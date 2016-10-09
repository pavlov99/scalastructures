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
    val h = new Heap(Vector(11))
      .insert(9)
      .insert(6)
      .insert(8)
      .insert(3)
    val (min1, h1) = h.extract
    val (min2, h2) = h1.extract
    val (min3, h3) = h2.extract
    assert(min1 == 3)
    assert(min2 == 6)
    assert(min3 == 8)
    assert(h.size == 5)
    assert(h1.size == 4)
    assert(h2.size == 3)
  }

  it should "allow simple init" in {
    assert(Heap(Vector[Int](): _*).keys == Vector())
    assert(Heap(1, 2, 3).keys == Vector(1, 2, 3))
  }

  it should "allow simple insert" in {
    assert((Heap(1) :+ 2).keys == Vector(1, 2))
  }

  it should "support custom ordering" in {
    val ordering = implicitly[Ordering[Int]].reverse
    val maxHeap = Heap(1, 4, 3, 5, 4, 2)(ordering)

    val (min1, h1) = maxHeap.extract
    val (min2, h2) = h1.extract
    val (min3, h3) = h2.extract
    assert(min1 == 5)
    assert(min2 == 4)
    assert(min3 == 4)
  }
}
