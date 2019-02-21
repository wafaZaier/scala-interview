package whats_wrong

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global

/*
Do you see anything that could lead to potential problems ?
What would you do to fix it ?
Do not mind about the not implemented code
*/

class WhatsWrong3 extends Actor {

  var internalState = "internal state"

  def receive: Receive = {
    case text: String => {
      val requestF: Future[String] = queryAsyncServer(text)
      requestF.onComplete {
        case Success(r) => handleResponse(r)
        case Failure(e) => e.printStackTrace()
      }
    }
  }

  def handleResponse(r: String) =  internalState = r // mutate internal state

  def queryAsyncServer(text: String): Future[String] = Future(text)
}
