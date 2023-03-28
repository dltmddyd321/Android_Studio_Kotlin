package com.sycompany.bojstep

class LinkedList<E> {
    //아무것도 설정되지 않은 LinkedList는 head가 없기 때문에 초기 값 null로 설정
    private var head: Node<E>? = null

    //노드 클래스. 제네릭 타입을 지정하고, 실제 data, next 노드 생성자를 구현
    private inner class Node<E>(
        var data: E,
        var next: Node<E>? = null
    )

    private fun addFirst(item: E) {
        head = Node(item)
    }

    fun add(item: E) {
        if (head == null) addFirst(item)
        else {
            var node = head
            //head가 null이 아니라면 마지막 노드까지 읽어낸 후, null을 가리키는 next를 새로 추가한 노드를 가리키게 한다.
            while (node?.next != null) node = node.next
            node?.next = Node(item)
        }
    }

    fun delete(item: E) {
        if (head == null) return println("노드가 존재하지 않습니다.")
        else {
            //head에 삭제하려는 데이터가 존재하면 다음 노드를 가리키게 하여 첫 번째 노드를 삭제
            if (head?.data == item) {
                head = head?.next
            } else { //중간 혹은 마지막에 데이터 존재 시 처리
                var node = head
                while (node?.next?.data != item) node = node?.next
                node?.next = node?.next?.next
            }
        }
    }

    fun check() {
        var node = head
        while (node != null) {
            println("${node.data}")
            node = node.next
        }
        println()
    }
}