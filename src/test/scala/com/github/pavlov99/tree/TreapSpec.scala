import org.scalatest._

import com.github.pavlov99.Treap


class TreapSpec extends FlatSpec {
  "Empty treap" should "have size 0" in {
    assert(Treap().isEmpty)
    assert(Treap().size == 0)
  }

  "NotEmpty treap" should "allow simple initialization" in {
    val t = Treap((1, 1), (2, 3))
    assert(!(t.isEmpty))
  }

  it should "allow split operation" in {
    val t = Treap((1, 1), (2, 3))
    t.split(2) match {
      case(t1, t2) => {
        assert(t1.size == 1)
        assert(t1.key == 1)
        assert(t1.priority == 1)

        assert(t2.size == 1)
        assert(t2.key == 2)
        assert(t2.priority == 3)
      }
    }
  }

  it should "be equal to itself" in {
    val t = Treap((1, 1), (2, 2))
    assert(t == t)
  }
}
