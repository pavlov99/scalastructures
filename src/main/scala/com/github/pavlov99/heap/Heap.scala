package com.github.pavlov99

class Heap[T: Ordering](val keys: Vector[T]) {
  val keyOrdering = implicitly[Ordering[T]]
  def size: Int = keys.size
  def isEmpty: Boolean = keys.isEmpty
  override def toString: String = keys.mkString("Heap(", ", ", ")")

  /** Insert element to the heap
  * Add element to the end of the heap and sift last element up.
  */
  def insert(key: T): Heap[T] = new Heap(keys :+ key) siftUp size
  def :+ (key: T): Heap[T] = insert(key)  // scalastyle:ignore method.name

  /** Extract minimal element according to current ordering.
   *  @returns (element, new heap)
   */
  def extract(): (T, Heap[T]) = (keys(0), new Heap(keys.last +: keys.tail.dropRight(1)) siftDown 0)

  /** Build heap from current vecotor of elements.
   *  Complexity: O(n)
   */
  def heapify(): Heap[T] = heapify0(size / 2 to 0 by -1)
  private def heapify0(indexes: Seq[Int]): Heap[T] = {
    if (indexes.isEmpty) {this}
    else {this siftDown indexes.head heapify0 indexes.tail}
  }

  /** Swap two elements in vector and return result vector. */
  private def swap(xs: Vector[T], i: Int, j: Int): Vector[T] =
    xs updated (i, xs(j)) updated (j, xs(i))

  /** Sift keys down. Method operates only on keys. */
  private def siftDownKeys(keys: Vector[T], i: Int): Vector[T] = {
    while (2 * i + 1 < size) {
      val left = 2 * i + 1  // left child
      val right = left + 1  // right child
      var j = left
      // keys(right) < keys(left)
      if (right < size && keyOrdering.compare(keys(right), keys(left)) < 0) {j = right}
      if (keyOrdering.compare(keys(i), keys(j)) <= 0) return keys
      return siftDownKeys(swap(keys, i, j), j)
    }
    keys
  }

  /** Sift Down element in the heap to fix heap's property.
   *  If element is greater than any of its childs, swap it with smaller one,
   *  otherwise do nothing.
   *  Complexity O(log n)
   */
  private def siftDown(i: Int): Heap[T] = new Heap(siftDownKeys(keys, i))

  /** Sift keys up. Method operates only on keys. */
  private def siftUpKeys(keys: Vector[T], i: Int): Vector[T] = {
    val j = (i - 1) / 2
    while (keyOrdering.compare(keys(i), keys(j)) < 0)
      return siftUpKeys(swap(keys, i, j), j)
    keys
  }

  /** Sift Up element in the heap to fix heap's property.
  *  If element is greater than parent, do nothing, otherwise swap it with
  *  parent and execure it for parent. As a result, element with small value
  *  goes up to the heap.
  *  Complexity O(log n)
  *  @return heap with sifted-up element.
  */
  private def siftUp(i: Int): Heap[T] = new Heap(siftUpKeys(keys, i))
}

object Heap {
  def apply[T: Ordering](keys: T*): Heap[T] = new Heap(Vector(keys: _*)) heapify
}
