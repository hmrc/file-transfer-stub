/*
 * Copyright 2024 HM Revenue & Customs
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

package uk.gov.hmrc.filetransfer.controller

import controllers.Assets
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar
import play.api.http.Status
import play.api.test.Helpers.*
import play.api.test.{FakeRequest, Helpers}

class TransferControllerControllerSpec
  extends AnyWordSpec
     with Matchers
     with MockitoSugar:

  private val fakeRequest = FakeRequest("GET", "/")
  private val assets = mock[Assets]
  private val controller = new TransferController(stubMessagesControllerComponents(), assets)

  "GET /envelopes" should:
    "return 200" in:
      val result = controller.list(fakeRequest)
      status(result) shouldBe Status.OK
