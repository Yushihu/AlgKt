package io.yushihu.tree.binary.common

interface Tree<V, T: TreeNode<V, T>> {
    fun createNode(value: V): T
}