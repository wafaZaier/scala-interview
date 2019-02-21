package whats_wrong

import akka.{ Done, NotUsed }
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

import scala.concurrent.Future

object BasicStream {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("Actor System")
    implicit val materializer = ActorMaterializer()

    val numbers = 1 to 1000

    val numberSource: Source[Int, NotUsed] = Source.fromIterator(() => numbers.iterator)

    //Only let pass even number through the flow
    val isEvenFlow: Flow[Int, Int, NotUsed] = Flow[Int].filter((num) => num % 2 == 0)

    //Create a Source of even numbers by combining the number Source with the even Flow
    val evenNumberSource: Source[Int, NotUsed] = numberSource.via(isEvenFlow)

    //A Sink that will write its input onto the console
    val consoleSink: Sink[Int, Future[Done]] = Sink.foreach[Int](println)

    //Connect the Source with the Sink and run it
    evenNumbersSource.runWith(consoleSink)
  }
}
