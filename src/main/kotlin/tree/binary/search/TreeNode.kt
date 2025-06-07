package io.yushihu.tree.binary.search

import io.yushihu.tree.binary.common.ComparableTreeNode
import io.yushihu.tree.binary.common.MutableTreeNode
import io.yushihu.tree.binary.common.goingRight
import io.yushihu.tree.binary.common.goingLeft

interface TreeNode<V: Comparable<V>, T: TreeNode<V, T>>
    : ComparableTreeNode<V, T>, MutableTreeNode<V, T> {

    fun findingPrecursor() = iterator {
        val entry = left
        if (entry == null) return@iterator
        yield(entry)
        yieldAll(entry.goingRight())
    }

    fun findingSuccessor() = iterator {
        val entry = right
        if (entry == null) return@iterator
        yield(entry)
        yieldAll(entry.goingLeft())
    }

    fun getPrecursor(): T? {
        var ret: T? = null
        for (pos in findingPrecursor()) {
            ret = pos
        }
        return ret
    }
    fun getSuccessor(): T? {
        var ret: T? = null
        for (pos in findingSuccessor()) {
            ret = pos
        }
        return ret
    }
}

fun <V: Comparable<V>, T: TreeNode<V, T>> T?.walking(value: V): Iterator<T> {
    return iterator {
        var cur = this@walking
        while (cur != null) {
            yield(cur)
            cur = if (value < cur.value) {
                cur.left
            } else {
                cur.right
            }
        }
    }
}