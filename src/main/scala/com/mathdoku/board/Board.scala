package com.mathdoku.board

class Board( val dimension : Int )
{
    // i -->
    // j
    // |
    // v
	lazy val squares = ( 0 until dimension ).map( i => ( 0 until dimension ).map( j => new Square( this, i, j ) ) )
	
	def row( index : Int ) = squares.view.map( _( index ) )
	
	def column( index : Int ) = squares.view( index )
	
	def apply( index : Int ) = squares.view( index )
}