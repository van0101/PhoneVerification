import org.json4s.DefaultFormats

import scala.util.matching.Regex

class AddressHandler(address: Address)
{

  var name = address.name
  var add_line1 = address.Add_line1
  var add_line2 = address.Add_line2
  var pin_str = address.pin_str
  var phone_str = address.phone_str
  var pin_int : Int = 0

  def verifyPIN(): Boolean =
  {
    pin_int = try pin_str.toInt catch{ case _ : Throwable => 0}

    if(pin_int == 0) false
    else true
  }

  def verifyPhone() : Boolean =
  {
    var regex = "^(\\+\\d{2}( )?)?(\\d{10})$"
    phone_str.matches(regex)
  }


}
