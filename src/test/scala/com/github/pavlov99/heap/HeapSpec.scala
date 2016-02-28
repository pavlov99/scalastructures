import org.scalatest._

import com.github.pavlov99.Heap

class HeapSpec extends FlatSpec {
  "Empty heap" should "have size 0" in {
    val h = new Heap(Vector[Int]())
    assert(h.size == 0)
  }

  it should "be empty" in {
    val h = new Heap(Vector[Int]())
    assert(h.isEmpty == true)
  }

  "Not empty heap" should "extract correct minimum" in {
    val h = new Heap(Vector(11)) :+ 9 :+ 9 :+ 6 :+ 8 :+ 3 :+ 4 :+ 12
    assert(h.extract._1 == 3)
  }
}
