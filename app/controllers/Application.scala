package controllers

import play.api.data.Form
import play.api.data.Forms.{single, text}
import play.api.mvc.{Action, Controller}

import org.squeryl.PrimitiveTypeMode._

import com.codahale.jerkson.Json
import models.{BarDb, Bar}


object Application extends Controller {

  val barForm = Form(single("name" -> text))

  def index = Action {
    Ok(views.html.index(barForm))
  }

  def addBar() = Action { implicit request =>
    barForm.bindFromRequest.value map { name =>
      inTransaction {
        val bar: Bar = new Bar(name)
        BarDb.bars.insert(bar)
        Redirect(routes.Application.index())
      }
    } getOrElse BadRequest
  }

  def listBars() = Action {
    inTransaction {
      val barsQuery = from(BarDb.bars)(bar =>
        select(bar)
      )
      val json = Json.generate(barsQuery)
      println(json)
      Ok(json).as("application/json")
    }
  }

}