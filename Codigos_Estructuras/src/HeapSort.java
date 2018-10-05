public class HeapSort {

    public static <T extends Comparable <? super T>> void sort(ArrayLinearList<T> theList){
        MaxHeap<T> heap = new MaxHeap();
        heap.initialize(theList);
        while(!theList.isEmpty())
        	theList.remove(theList.size() - 1);
        while(!heap.isEmpty())
        	theList.add(heap.removeMax());
    }

    public static void main(String[] args){
        ArrayLinearList<Integer> lista = new ArrayLinearList<Integer>();
        lista.add(3);
        lista.add(2);
        lista.add(4);
        lista.add(1);
        lista.add(6);
        lista.add(9);
        lista.add(8);
        lista.add(7);
        lista.add(5);
        lista.add(0);
        System.out.println("The list is: ");
        System.out.println(lista);
        sort(lista);
        System.out.println("The list in descending order is: ");
        System.out.println(lista);
    }
}
