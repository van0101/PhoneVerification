import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.native.Serialization
import org.json4s.native.Serialization.{read, write}


object Main
{
  def main(args: Array[String]): Unit =
  {
    implicit val formats: Formats = Serialization.formats(NoTypeHints)

    val source = scala.io.Source.fromFile("V:\\AddressesTxt.txt")


    val addressContainer: AddressContainer = getValue(source.mkString)


    var addressList = addressContainer.address


    for( address <- addressList)
    {
      stringValidation(address)
    }

    println("Correct List ")
    for(i <- correctList)
    {
      println(i.name)
    }

    println("Incorrect List ")
    for(i <- incorrectList) println(i.name)
  }

  var correctList : List[AddressHandler] = List.empty
  var incorrectList : List[AddressHandler] = List.empty

  def stringValidation(address : Address): Unit =
  {
    // Single Line of address
    var addressHandler : AddressHandler = new AddressHandler(address)

    if(addressHandler.verifyPIN() && addressHandler.verifyPhone()) correctList ::= addressHandler
    else incorrectList ::= addressHandler

  }

  def getValue(line : String): AddressContainer =
  {
    implicit val formats = DefaultFormats
    val jsonStr = parse(line)
    val AddObj = jsonStr.extract[AddressContainer]
    AddObj
  }



}
