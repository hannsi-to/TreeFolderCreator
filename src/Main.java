import java.io.File;

public class Main {
    public static String path = "D:/test";//ディレクトリ指定;
    public static int startDepth = 0;//サブディレクトリの開始位置
    public static int depth = 4;//サブディレクトリの深さ
    public static int startPerDirectory = 0;//各ディレクトリ内のファイルの開始位置
    public static int filesPerDirectory = 9;//各ディレクトリ内のファイル数
    public static String name = "";//ファイルの名前

    public static void main(String[] args) {
        try {
            File rootDirectory = new File(path);
            if (!rootDirectory.exists()) {
                rootDirectory.mkdirs();
            }

            createDirectoryTree(rootDirectory, depth, filesPerDirectory);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void createDirectoryTree(File directory, int depth, int filesPerDirectory) {
        if (depth < startDepth) {
            return;
        }

        for (int i = startPerDirectory; i <= filesPerDirectory; i++) {
            File subDirectory = new File(directory, name + i);
            boolean check = subDirectory.mkdirs();
            System.out.println(subDirectory.getPath() + " : " + check);
            createDirectoryTree(subDirectory, depth - 1, filesPerDirectory);
        }
    }
}