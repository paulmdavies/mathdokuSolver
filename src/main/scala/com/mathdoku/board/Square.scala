package com.mathdoku.board

class Square( private val board : Board, val row : Int, val column : Int, private var valueOpt : Option[Int] = None ) 
{
    def complete() : Boolean = valueOpt != None
    
	def value() : Int = valueOpt.get
	
	def value_=( newValue : Int ) : Unit = valueOpt = Some( newValue )
	
	def clear() : Unit = valueOpt = None
}