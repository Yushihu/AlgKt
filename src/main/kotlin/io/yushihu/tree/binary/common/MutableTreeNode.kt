package io.yushihu.tree.binary.common

interface MutableTreeNode<
    V,
    T: MutableTreeNode<V, T>,
>: TreeNode<V, T> {
    override val left: T? get() = leftProp
    override val right: T? get() = rightProp
    override val value: V get() = valueProp

    var leftProp: T?
    var rightProp: T?
    var valueProp: V

    fun applyValue(other: T) {
        valueProp = other.value
    }

}

fun <V, T: MutableTreeNode<V, T>> T.rotateLeft(): T {
    val newParent = rightProp ?: error(IllegalStateException("Cannot rotate left on a node with no right child"))
    val rl = newParent.leftProp
    newParent.leftProp = this
    this.rightProp = rl
    return newParent
}

fun <V, T: MutableTreeNode<V, T>> T.rotateRight(): T {
    val newParent = leftProp ?: error(IllegalStateException("Cannot rotate right on a node with no left child"))
    val lr = newParent.rightProp
    newParent.rightProp = this
    this.leftProp = lr
    return newParent
}
