package io.yushihu.tree.binary.search

import io.yushihu.tree.binary.common.Tree as CommonTree

interface Tree<
    V: Comparable<V>,
    T: TreeNode<V, T>,
> : CommonTree<V, T> {

    var root: T?

    fun getFirst(value: V): T {
        return getFirstOrNull(value) ?: throw NoSuchElementException("Value $value not found in the tree.")
    }

    fun getFirstOrNull(value: V): T? {
        var ret: T? = null
        for (pos in root.walking(value)) {
            if (pos.value == value) {
                ret = pos
                break
            }
        }
        return ret
    }

    fun insert(value: V): T{
        var entry = root

        if (entry == null) {
            val node = createNode(value)
            root = node
            return node
        }

        val iterator = entry.walking(value)
        var cur = iterator.next()
        for (next in iterator) {
            cur = next
        }

        val node = createNode(value)
        if (value < cur.value) {
            cur.leftProp = node
        } else {
            cur.rightProp = node
        }
        return node

    }

    fun deleteFirst(value: V): Boolean {
        var target: T? = null
        var parent: T? = null

        for (pos in root.walking(value)) {
            if (pos.value == value) {
                target = pos
                break
            }
            parent = pos
        }

        if (target == null) {
            return false
        }

        val it = if (target.right != null) {
            target.findingSuccessor()
        } else if (target.left != null) {
            target.findingPrecursor()
        } else {
            if (parent == null) {
                root = null
            } else if (target === parent.left) {
                parent.leftProp = null
            } else {
                parent.rightProp = null
            }
            return true
        }
        var replacementParent = target
        var replacement = it.next()
        for (next in it) {
            replacementParent = replacement
            replacement = next
        }

        target.applyValue(replacement)

        val child = if (replacement.left != null) {
            replacement.left
        } else {
            replacement.right
        }
        if (replacement === replacementParent.left) {
            replacementParent.leftProp = child
        } else {
            replacementParent.rightProp = child
        }

        return true
    }

}
