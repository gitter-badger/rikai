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

package ai.eto.rikai.sql.spark.execution

import org.apache.spark.sql.{Row, SparkSession}
import ai.eto.rikai.sql.model.Model
import org.apache.spark.sql.catalyst.expressions.Attribute

case class ShowModelsCommand() extends ModelCommand {

  override val output: Seq[Attribute] = ModelCommand.output

  override def run(spark: SparkSession): Seq[Row] = {
    val models = catalog(spark).listModels()

    models.map { model: Model =>
      Row(model.name, model.uri, Model.serializeOptions(model.options))
    }
  }
}
