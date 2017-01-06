import sys
class BSTNode(object):
    def __init__(self,data):
        self._data = data
        self._left_child = None
        self._right_child = None

    def get_data(self):
        return self._data

    def get_left(self):
        return self._left_child

    def get_right(self):
        return self._right_child

    def set_left(self, left_child):
        self._left_child = left_child

    def set_right(self, right_child):
        self._right_child = right_child

    def __str__(self):
        return str(self.__data__)


def inorder_traversal(node):
    if node is None:
        return
    left = node.get_left()
    inorder_traversal(left)
    print node.get_data()
    right = node.get_right()
    inorder_traversal(right)

def preorder_traversal(node):
    if node is None:
        return
    print node.get_data()
    left = node.get_left()
    preorder_traversal(left)
    right = node.get_right()
    preorder_traversal(right)

def postorder_traversal(node):
    if node is None:
        return
    left = node.get_left()
    postorder_traversal(left)
    right = node.get_right()
    postorder_traversal(right)
    print node.get_data()

def insert(root, node):
    data_r = root.get_data()
    data_n = node.get_data()
    if data_n < data_r:
        l_child = root.get_left()
        if l_child is None:
            root.set_left(node)
            return
        else:
            insert(l_child, node)
    else:
        r_child = root.get_right()
        if r_child is None:
            root.set_right(node)
            return
        else:
            insert(r_child, node)

def insert2(root, num):
    data_r = root.get_data()
    if num < data_r:
        l_child = root.get_left()
        if l_child is None:
            new_node = BSTNode(num)
            root.set_left(new_node)
        else:
            insert2(l_child, num)
    else:
        r_child = root.get_right()
        if r_child is None:
            new_node = BSTNode(num)
            root.set_right(new_node)
        else:
            insert2(r_child, num)

def main(argv=None):
    if argv is None:
        argv = sys.argv

    root = BSTNode(4)

    node0 = BSTNode(0)
    node1 = BSTNode(1)
    node2 = BSTNode(2)
    node3 = BSTNode(3)
    node5 = BSTNode(5)
    node6 = BSTNode(6)
    node7 = BSTNode(7)
    node8 = BSTNode(8)
    node9 = BSTNode(9)
    nodes = [node0, node1, node2, node3, node5, node6, node7, node8, node9]
    nums = [0,1,2,3,5,6,7,8,9]
    #for node in nodes:
    #    insert(root, node)
    for num in nums:
        insert2(root,num)
    """
    node1.set_left(node0)
    node2.set_left(node1)
    node2.set_right(node3)
    node5.set_right(node9)
    node9.set_left(node7)
    node7.set_left(node6)
    node7.set_right(node8)
    root.set_left(node2)
    root.set_right(node5)
    """

    inorder_traversal(root)
    #preorder_traversal(root)
    #postorder_traversal(root)

if __name__ == '__main__':
    main()

