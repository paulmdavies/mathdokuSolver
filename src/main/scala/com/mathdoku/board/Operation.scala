package com.mathdoku.board

object Operation
{
	def addition( target : Int ) : ( Seq[Square] => Operation ) =
	{
		squares => new AdditionOperation( target )( squares )
	}
}

trait Operation
{
    /** What's the target of the group? */
	val target : Int
	
	/**
	 *  Is the group valid?
	 * 	@return None if not complete, False if invalid, True if valid 
	 */	
	def validate() : Option[Boolean]
	
	/**
	 * 	Is the group complete, that is, is every square filled?
	 *  @return Are all the squares filled?
	 */
	def complete() : Boolean = squares.forall( _.complete )
	
	/** The minimum number of squares in a given type of group */
	val minSquares : Int
	
	/** The maximum number of squares in a given type of group: will be Non if there's no maximum */
	val maxSquares : Option[Int]
	
	/** The squares in the group */
	val squares : Seq[Square]
}

class NoOperation( val target : Int )( val squares : Seq[Square] ) extends Operation
{
	val minSquares = 1
	val maxSquares = Some( 1 )
	
	def validate() : Option[Boolean] = complete match
	{
	    case false => None
	    case true => Some( squares.map( _.value ).forall( _ == target ) )
	}
}

class AdditionOperation( val target : Int )( val squares : Seq[Square] ) extends Operation
{
	val minSquares = 2
	val maxSquares = None
	
	def validate() : Option[Boolean] = complete match
	{
	    case false => None
	    case true => Some( squares.map( _.value ).sum == target )
	}
}

class SubtractionOperation( val target : Int )( val squares : Seq[Square] ) extends Operation
{
	val minSquares = 2
	val maxSquares = Some( 2 )
	
	def validate() : Option[Boolean] = complete match
	{
	    case false => None
	    case true => 
        {
            val values = squares.map( _.value )
            Some( math.abs( values.head - values.last ) == target )
        }
	}
}

class MultiplicationOperation( val target : Int )( val squares : Seq[Square] ) extends Operation
{
	val minSquares = 2
	val maxSquares = None
	
	def validate() : Option[Boolean] = complete match
	{
	    case false => None
	    case true => Some( squares.map( _.value ).product == target )
	}
}

class DivisionOperation( val target : Int )( val squares : Seq[Square] ) extends Operation
{
	val minSquares = 2
	val maxSquares = Some( 2 )
	
	def validate() : Option[Boolean] = complete match
	{
	    case false => None
	    case true => 
        {
            val values = squares.map( _.value )
            val numerator = values.head 
            val denominator = values.last
            Some( numerator % denominator == 0 && numerator / denominator == target )
        }
	}    
}