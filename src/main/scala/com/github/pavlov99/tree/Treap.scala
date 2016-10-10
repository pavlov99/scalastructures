package com.github.pavlov99


abstract sealed class Treap[+K <% Ordered[K], +P <% Ordered[P]]{
  def key: K
  def priority: P
  def left: Treap[K, P]
  def right: Treap[K, P]
  def size: Int
  def isEmpty: Boolean

  override def toString: String =
    if (isEmpty) {
      "."
    } else {
      "{" + left + "(" + key + ", " + priority + ")" + right + "}"
    }

  override def equals(that: Any): Boolean =
    that match {
      case that: Treap[K, P] =>
        if (isEmpty && that.isEmpty) {
          true
        } else if (isEmpty ^ that.isEmpty) {
          false
        } else {
          key == that.key && priority == that.priority &&
          left == that.left && right == that.right
        }
      case _ => false
    }

  override def hashCode: Int = {
    if (isEmpty) {
      0
    } else {
      key.hashCode + priority.hashCode + left.hashCode + right.hashCode
    }
  }

  def split[K1 >: K <% Ordered[K1]](k: K1): (Treap[K, P], Treap[K, P]) =
    if (isEmpty) {
      (Treap.empty[K, P], Treap.empty[K, P])
    } else if (k > key) {
      right.split(k) match {
        case (t1, t2) => (Treap.make(key, priority, left, t1), t2)
      }
    } else {
      left.split(k) match {
        case (t1, t2) => (t1, Treap.make(key, priority, t2, right))
      }
    }

  def merge[K1 >: K <% Ordered[K1], P1 >: P <% Ordered[P1]](t: Treap[K1, P1]): Treap[K1, P1] =
    if (t.isEmpty) {
      this
    } else if (isEmpty) {
      t
    } else if (priority > t.priority) {
      Treap.make(key, priority, left, right merge t)
    } else {
      Treap.make(t.key, t.priority, this merge t.left, t.right)
    }

  // scalastyle:off method.name
  def ++[K1 >: K <% Ordered[K1], P1 >: P <% Ordered[P1]](t: Treap[K1, P1]): Treap[K1, P1] = merge(t)
  // scalastyle:on

  def insert[K1 >: K <% Ordered[K1], P1 >: P <% Ordered[P1]](k: K1, p: P1): Treap[K1, P1] =
    split(k) match {
      case(t1, t2) => t1.merge(Treap.make(k, p)).merge(t2)
    }

  // scalastyle:off method.name
  def :+[K1 >: K <% Ordered[K1], P1 >: P <% Ordered[P1]](k: K1, p: P1): Treap[K1, P1] = insert(k, p)
  // scalastyle:on

  def head: (K, P) = (key, priority)
  def tail: Treap[K, P] = left merge right
  def extract(): ((K, P), Treap[K, P]) = (head, tail)

  def remove[K1 >: K <% Ordered[K1], P1 >: P <% Ordered[P1]](k: K1): Treap[K1, P1] =
    if (isEmpty) {
      throw new RuntimeException("An empty tree.")
    } else if (k < key) {
      Treap.make(key, priority, left.remove(k), right)
    } else if (k > key) {
      Treap.make(key, priority, left, right.remove(k))
    } else {
      left merge right
    }

  def contains[K1 >: K <% Ordered[K1]](k: K1): Boolean =
    if (isEmpty) {
      false
    } else if (k == key) {
      true
    } else if (k < key) {
      left.contains(k)
    } else {
      right.contains(k)
    }

  def height: Int = if (isEmpty) 0 else 1 + math.max(left.height, right.height)

  def min: (K, P) = {
    def loop(t: Treap[K, P], k: K, p: P): (K, P) =
      if (t.isEmpty) (k, p) else loop(t.left, t.key, t.priority)
    if (isEmpty) {
      throw new RuntimeException("An empty tree.")
    } else {
      loop(left, key, priority)
    }
  }

  def max: (K, P) = {
    def loop(t: Treap[K, P], k: K, p: P): (K, P) =
      if (t.isEmpty) (k, p) else loop(t.right, t.key, t.priority)
    if (isEmpty) {
      throw new RuntimeException("An empty tree.")
    } else {
      loop(right, key, priority)
    }
  }
}

case object Leaf extends Treap[Nothing, Nothing] {
  def key: Nothing = throw new RuntimeException("An empty tree.")
  def priority: Nothing = throw new RuntimeException("An empty tree.")
  def left: Treap[Nothing, Nothing] = throw new RuntimeException("An empty tree.")
  def right: Treap[Nothing, Nothing] = throw new RuntimeException("An empty tree.")
  def size: Int = 0
  def isEmpty: Boolean = true
}

case class Branch[K <% Ordered[K], P <% Ordered[P]](
    key: K,
    priority: P,
    left: Treap[K, P],
    right: Treap[K, P],
    size: Int) extends Treap[K, P]{
  def isEmpty: Boolean = false
}

object Treap {
  def empty[K, P]: Treap[K, P] = Leaf
  def make[K <% Ordered[K], P <% Ordered[P]](
    k: K,
    p: P,
    l: Treap[K, P] = Leaf,
    r: Treap[K, P] = Leaf): Treap[K, P] = Branch(k, p, l, r, l.size + r.size + 1)

  def apply[K <% Ordered[K], P <% Ordered[P]](xs: (K, P)*): Treap[K, P] =
    xs.foldLeft(Treap.empty[K, P])({case(t, (k, p)) => t.insert(k, p)})
}
