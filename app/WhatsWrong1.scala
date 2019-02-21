package whats_wrong

object WhatsWrong1 {

  trait Interface {
    val city: String
    val support: String = s"Ici c'est $city !"
  }

  case object Supporter extends Interface {

    override val city = "Paris"
  }

  Supporter.city // this print "Paris"
  Supporter.support // this print  "Ici c'est null"  city = null because it is not initialized in the trait.
                    // and  "support" is a "val" so it evaluates when defined.
                    // so we should use "def": evaluates on every call
                    // def support: String = s"Ici c'est $city !" => it print "Ici c'est Paris"
}
