/*
 * Copyright 2022 HM Revenue & Customs
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

package uk.gov.hmrc.filetransfer

import java.util.UUID

import play.api.libs.json._
import play.api.mvc.PathBindable

case class EnvelopeId(value: String = UUID.randomUUID().toString) extends AnyVal {
  override def toString = value
}

object EnvelopeId {
  implicit val writes: Writes[EnvelopeId] =
    (id: EnvelopeId) => JsString(id.value)

  implicit val reads: Reads[EnvelopeId] =
    (json: JsValue) =>
      json match {
        case JsString(value) => JsSuccess(EnvelopeId(value))
        case _ => JsError("invalid envelopeId")
      }

  implicit val binder: PathBindable[EnvelopeId] =
    new PathBindable[EnvelopeId] {
      override def bind(key: String, value: String): Either[String, EnvelopeId] =
        Right(EnvelopeId(value))
      override def unbind(key: String, value: EnvelopeId): String =
        value.value
    }
}
