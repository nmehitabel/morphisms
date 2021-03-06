package io.mehitabel.morphism
package parametric

sealed trait Exp[A]
final case class IntValue[A](v: Int) extends Exp[A]
final case class DecValue[A](v: Double) extends Exp[A]
final case class Sum[A](exp1: A, exp2: A) extends Exp[A]
final case class Multiply[A](exp1: A, exp2: A) extends Exp[A]
final case class Divide[A](exp1: A, exp2: A) extends Exp[A]
final case class Square[A](exp: A) extends Exp[A]

object Example {
  val exp2: Exp[Exp[Exp[Unit]]] =
    Divide[Exp[Exp[Unit]]](
      DecValue[Exp[Unit]](5.2),
      Sum[Exp[Unit]](
        IntValue[Unit](10),
        IntValue[Unit](5)
      )
    )
}

object FixExample {
  val fExp1: Fix[Exp] =
    Fix(Sum[Fix[Exp]](
      Fix(IntValue[Fix[Exp]](10)),
      Fix(IntValue[Fix[Exp]](5))
    ))

  // Note nested Fix[_] type ascriptions not necessary
  val fExp2: Fix[Exp] =
    Fix(Divide(
      Fix(DecValue(5.2)),
      Fix(Sum(
        Fix(IntValue(5)),
        Fix(IntValue(10))
      ))
    ))
}
