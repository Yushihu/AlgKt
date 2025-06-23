

import io.yushihu.tree.binary.search.Tree
import io.yushihu.tree.binary.search.TreeNode
import org.junit.jupiter.api.Test
import kotlin.random.Random

class Node(
    left: Node?,
    right: Node?,
    value: Int,
): TreeNode<Int, Node> {
    override var leftProp: Node? = left
    override var rightProp: Node? = right
    override var valueProp: Int = value

}

class BST: Tree<Int, Node> {
    override var root: Node? = null

    override fun createNode(value: Int): Node {
        return Node(null, null, value)
    }
}

open class BSTTest {
    val utils = BSTUtils()

    @Test
    fun testCRD() {
        utils.testCRD(BST())
    }


}

