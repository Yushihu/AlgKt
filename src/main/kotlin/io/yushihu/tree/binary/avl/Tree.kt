package io.yushihu.tree.binary.avl

import io.yushihu.tree.binary.search.walking
import kotlin.math.abs
import kotlin.math.max
import io.yushihu.tree.binary.search.Tree as _ST

interface Tree<V: Comparable<V>, T: TreeNode<V, T>>: _ST<V, T> {
    val threshold: Int

    override fun insert(value: V): T{
        var entry = root

        if (entry == null) {
            val node = createNode(value)
            root = node
            return node
        }
        val parents = entry.walking(value).asSequence().toList()
        val parent = parents.last()

        val node = createNode(value)
        if (value < parent.value) {
            parent.leftProp = node
        } else {
            parent.rightProp = node
        }

        parents.balance()

        return node

    }

    override fun deleteFirst(value: V): Boolean {
        var target: T? = null
        val parents = mutableListOf<T>()

        for (pos in root.walking(value)) {
            if (pos.value == value) {
                target = pos
                break
            }
            parents.add(pos)
        }

        if (target == null) {
            return false
        }

        if (target.right != null) {
            parents.add(target)

            val it = target.findingSuccessor()
            var replacement = it.next()
            for (next in it) {
                parents.add(replacement)
                replacement = next
            }
            target.applyValue(replacement)
            val child = replacement.right
            val parent = parents.last()
            if (replacement === parent.left) {
                parent.leftProp = child
            } else {
                parent.rightProp = child
            }
        } else if (target.left != null) {
            parents.add(target)
            val it = target.findingPrecursor()
            var replacement = it.next()
            for (next in it) {
                parents.add(replacement)
                replacement = next
            }
            target.applyValue(replacement)
            val child = replacement.left
            val parent = parents.last()
            if (replacement === parent.left) {
                parent.leftProp = child
            } else {
                parent.rightProp = child
            }
        } else {
            if (parents.isEmpty()) {
                root = null
            } else {
                val parent = parents.last()
                if (target === parent.left) {
                    parent.leftProp = null
                } else {
                    parent.rightProp = null
                }
            }
        }

        parents.balance()

        return true
    }

    fun T.isUnbalance(): Boolean {
        return abs(leftHeight - rightHeight) > threshold
    }

    fun T.balance(): T {
        return if (leftHeight > rightHeight) {
            if (leftProp!!.leftHeight < leftProp!!.rightHeight) {
                leftProp = leftProp!!.rotateLeft()
            }
            rotateRight()
        } else {
            if (rightProp!!.leftHeight > rightProp!!.rightHeight) {
                rightProp = rightProp!!.rotateRight()
            }
            rotateLeft()
        }
    }

    fun List<T>.balance() {
        for (i in lastIndex downTo 0) {
            val parent = get(i)
            val preHeight = parent.height
            var curHeight = 1 + max(parent.leftHeight, parent.rightHeight)
            parent.heightProp = curHeight

            if (parent.isUnbalance()) {
                val np = parent.balance()
                if (i == 0) {
                    root = np
                } else {
                    get(i - 1).let { grandParent ->
                        if (grandParent.leftProp == parent) {
                            grandParent.leftProp = np
                        } else {
                            grandParent.rightProp = np
                        }
                    }
                }
                curHeight = np.height
            }

            if (preHeight == curHeight) break
        }
    }

}
