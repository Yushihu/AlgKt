package io.yushihu.tree.binary.common

interface ComparableTreeNode<V: Comparable<V>, T: TreeNode<V, T>>: TreeNode<V, T> {
    operator fun compareTo(node: T): Int {
        return compareTo(node.value)
    }

    operator fun compareTo(value: V): Int {
        return when {
            this.value < value -> -1
            this.value > value -> 1
            else -> 0
        }
    }
}
