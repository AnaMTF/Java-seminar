//import java.util.concurrent.ForkJoinPool;
//import java.util.concurrent.RecursiveTask;
//
//public class Seminar06_ForkJoinMax {
//    static class MaxTask extends RecursiveTask<Integer> {
//
//        private final int[] vector;
//        private final int indexStart, indexEnd;
//
//        public MaxTask(int[] vector, int indexStart, int indexEnd) {
//            this.vector = vector;
//            this.indexStart = indexStart;
//            this.indexEnd = indexEnd;
//        }
//
//        @Override
//        protected Integer compute() {
//            if (indexEnd == indexStart) {
//                return vector[indexStart];
//            }
//
//            var indexMiddle = (indexStart + indexEnd) / 2;
//            var subTask1 = new Scratchpad.MaxTask(vector, indexStart, indexMiddle);
//            var subTask2 = new Scratchpad.MaxTask(vector, indexMiddle + 1, indexEnd);
//            invokeAll(subTask1, subTask2);
//
//            return Math.max(subTask1.join(), subTask2.join());
//        }
//    }
//
//    public static void main(String[] args) {
//        var vector = new int[]{1, 2, 3, 5, 21, 1, 4, 6, 7, 10, 3};
//
//        ForkJoinPool pool = new ForkJoinPool(4);
//        var taskMax = new Scratchpad.MaxTask(vector, 0, vector.length - 1);
//        pool.invoke(taskMax);
//        System.out.println("Valoarea maxima este " + taskMax.join());
//    }
//}
