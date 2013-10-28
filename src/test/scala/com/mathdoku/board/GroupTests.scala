package com.mathdoku.board

import org.scalatest.FlatSpec

class GroupTests extends FlatSpec
{    
	behavior of "A Group"
	
	it should "have the correct arity" in
	{
	    val b = new Board( 6 )
	    val g = Group.fromCoordinates( b, Operation.addition( 10 ), Seq( ( 0,0 ), ( 0,1 ), ( 0, 2 ) ) )
	    assert( g.arity === 1 )
	    val g2 = Group.fromCoordinates( b, Operation.addition( 10 ), Seq( ( 0,0 ), ( 0,1 ), ( 1,1 ) ) )
	    assert( g2.arity === 2 )
	    val g3 = Group.fromCoordinates( b, Operation.addition( 10 ), ( 0 until 4 ).flatMap( i => Seq( ( 0, i ), ( i, 0 ), ( 3, i ), ( i, 3 ) ) ) )
	    assert( g3.arity === 4)
	}
}