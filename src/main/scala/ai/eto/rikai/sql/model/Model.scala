/*
 * Copyright 2021 Rikai authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.eto.rikai.sql.model

import scala.collection.JavaConverters.mapAsJavaMap

/**
  * A Machine Learning Model in Rikai Catalog.
  */
trait Model {

  /** Model Name */
  val name: String

  /** Model URI in the registry */
  val uri: String

  /** Model Options. */
  var options: Map[String, String] = Map.empty

  /** Return options as java Map, so that it is easily accessible in Python via py4j. */
  final def javaOptions: java.util.Map[String, String] = mapAsJavaMap(options)

}

object Model {

  /** Model Name Pattern */
  val namePattern = """[a-zA-Z]\w{0,255}""".r

  @throws[ModelNameException]
  def verifyName(name: String): Unit = {
    if (!name.matches(namePattern.regex)) {
      throw new ModelNameException(s"Model name '${name}' is not valid")
    }
  }
}

class ModelNameException(message: String) extends Exception(message);
