package com.mathdoku.board

object Group
{
	def fromSquares( board : Board, operation : ( Seq[Square] => Operation ), squares : Seq[Square] ) : Group =
	{
	    new Group( board, operation, squares )
	}
	
	def fromCoordinates( board : Board, operation : ( Seq[Square] => Operation ), squareCoordinates : Seq[(Int,Int)] ) : Group =
	{
	    new Group( board, operation, squareCoordinates.map{ case ( i, j ) => board( i )( j ) } )
    }
}

class Group private ( private val board : Board, op : ( Seq[Square] => Operation ), val squares : Seq[Square] )
{
	assert( !squares.isEmpty, "A group must contain at least one square" )
	
	val operation = op( squares )
	
	/**
	 *  The arity of a group defines the maximum number of repeats of any given number that may appear in a group, regardless of operation.
	 */
	lazy val arity =
	{
	    def adjacents( square : Square, squares : Seq[Square] ) : Seq[Square] =
		{
		    squares.filter( s => ( s.row - square.row ).abs == 1 || ( s.column - square.column ) == 1 )
		}
		
		def squareArity( square : Square, squares : Seq[Square] ) : Int =
		{
		    var traversed = Set[Square]()
		    var unique = Set[Square]()
		    
		    visit( square )
		    
		    def visit( square : Square ) : Unit =
		    {
		        val uniqueRows = unique.map( _.row )
		        val uniqueColumns = unique.map( _.column )
		        if ( !uniqueRows.contains( square.row ) && !uniqueColumns.contains( square.column ) ) unique += square
		        traversed += square
		        if ( traversed.size != squares.size ) adjacents( square, squares ).filter( s => !traversed.contains( s ) ).map( visit )
		    }
		    
		    unique.size
		}
		
		squares.map( s => squareArity( s, squares ) ).max
	}
}