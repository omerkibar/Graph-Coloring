import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        Graph graph = new Graph("test1.txt");
        int numberOfVertices = graph.vertices.size();
        int[] colors = new int[numberOfVertices];
        int numberOfColors = graph.colorGraphBySorting(colors);
        
        int[] newColors = new int[numberOfVertices];
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
            int newNumberOfColors = graph.colorGraph(newColors, numberOfColors);
            if(newNumberOfColors == -1)continue;
            System.out.println("current number of colors = "+newNumberOfColors);
            colors = Arrays.copyOf(newColors, numberOfVertices);
            numberOfColors = newNumberOfColors;
        }
        System.out.println(numberOfColors);
        for (int i : colors) {
            System.out.print(i+" ");
        }
        System.out.println("\n"+graph.validateColoring(numberOfColors, colors));
    }
}