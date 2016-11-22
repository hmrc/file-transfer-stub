/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.filetransfer.controllers.transfer

/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import controllers.Assets
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent}
import uk.gov.hmrc.filetransfer.EnvelopeId
import uk.gov.hmrc.play.microservice.controller.BaseController

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps
import play.api.libs.concurrent.Execution.Implicits._
object TransferController extends TransferController

class TransferController()(implicit executionContext: ExecutionContext) extends BaseController {

  def list() = Action.async { implicit request =>
    val result =
      s"""
         |{
         |  "_links": {
         |    "self": {
         |      "href": "http://full.url.com/file-transfer/envelopes?destination=DMS"
         |    }
         |  },
         |  "_embedded": {
         |    "envelopes": [
         |      {
         |        "id": "0b215e97-11d4-4006-91db-c067e74fc653",
         |        "destination": "DMS",
         |        "application": "application:digital.forms.service/v1.233",
         |        "_embedded": {
         |          "files": [
         |            {
         |              "href": "/file-upload/envelopes/b215e97-11d4-4006-91db-c067e74fc653/files/1/content",
         |              "name": "original-file-name-on-disk.docx",
         |              "contentType": "application/vnd.oasis.opendocument.spreadsheet",
         |              "length": 1231222,
         |              "created": "2016-03-31T12:33:45Z",
         |              "_links": {
         |                "self": {
         |                  "href": "/file-upload/envelopes/b215e97-11d4-4006-91db-c067e74fc653/files/1"
         |                }
         |              }
         |            },
         |            {
         |              "href": "/file-upload/envelopes/b215e97-11d4-4006-91db-c067e74fc653/files/2/content",
         |              "name": "another-file-name-on-disk.docx",
         |              "contentType": "application/vnd.oasis.opendocument.spreadsheet",
         |              "length": 112221,
         |              "created": "2016-03-31T12:33:45Z",
         |              "_links": {
         |                "self": {
         |                  "href": "/file-upload/envelopes/b215e97-11d4-4006-91db-c067e74fc653/files/2"
         |                }
         |              }
         |            }
         |          ]
         |        },
         |        "_links": {
         |          "self": {
         |            "href": "/file-transfer/envelopes/0b215e97-11d4-4006-91db-c067e74fc653"
         |          },
         |          "package": {
         |            "href": "/file-transfer/envelopes/0b215e97-11d4-4006-91db-c067e74fc653",
         |            "type": "application/zip"
         |          },
         |          "files": [
         |            {
         |              "href": "/files/1",
         |              "href": "/files/2"
         |            }
         |          ]
         |        }
         |      }
         |    ]
         |  }
         |}
       """.stripMargin

    Future.successful(Ok(Json.parse(result)))
  }

  def download(envelopeId: EnvelopeId): Action[AnyContent] = {
    Assets.at(path = "/public", file = "transfer/envelope.zip")
  }

  def delete(envelopeId: EnvelopeId) = Action.async { implicit request =>
    Future.successful(Ok)
  }
}
