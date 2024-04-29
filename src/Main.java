import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static String path = "D:/test";//ディレクトリ指定;
    public static int startDepth = 0;//サブディレクトリの開始位置
    public static int depth = 3;//サブディレクトリの深さ
    public static Mode mode = Mode.ALL;//使用する文字 NUMBER : 数字0~9     ALL : 文字全部(ファイル名に使えない文字は除く)
    public static int startPerDirectory = 0;//各ディレクトリ内のファイルの開始位置(mode NUMBER 使用時のみ)
    public static int filesPerDirectory = 9;//各ディレクトリ内のファイル数(mode NUMBER 使用時のみ)
    public static String name = "";//ファイルの名前

    public static List<String> allString = getAllString();
    public static int fileCount = 0;

    public static void main(String[] args) {
        long tookTime = calculate(() -> {
            try {
                File rootDirectory = new File(path);
                if (!rootDirectory.exists()) {
                    rootDirectory.mkdirs();
                }

                if (Mode.NUMBER == mode) {
                    createDirectoryTreeNumber(rootDirectory, depth, filesPerDirectory);
                } else if (Mode.ALL == mode) {
                    createDirectoryTreeAll(rootDirectory, depth);
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });

        System.out.println("TotalTime : " + tookTime + "ms");
    }

    public static void createDirectoryTreeAll(File directory, int depth) {
        if (depth < startDepth) {
            return;
        }

        for (String string : allString) {
            File subDirectory = new File(directory, name + string);
            boolean check = subDirectory.mkdirs();
            fileCount++;
            System.out.println(subDirectory.getPath() + " : " + check + "  |  TotalFolders : " + fileCount);
            createDirectoryTreeAll(subDirectory, depth - 1);
        }
    }

    public static void createDirectoryTreeNumber(File directory, int depth, int filesPerDirectory) {
        if (depth < startDepth) {
            return;
        }

        for (int i = startPerDirectory; i <= filesPerDirectory; i++) {
            File subDirectory = new File(directory, name + i);
            boolean check = subDirectory.mkdirs();
            System.out.println(subDirectory.getPath() + " : " + check + "  |  FileFolders : " + fileCount);
            createDirectoryTreeNumber(subDirectory, depth - 1, filesPerDirectory);
        }
    }

    public static List<String> getAllString() {
        List<String> validCharacters = new ArrayList<>();

        for (char c = 'A'; c <= 'Z'; c++) {
            validCharacters.add(String.valueOf(c));
        }
        for (char c = 'a'; c <= 'z'; c++) {
            validCharacters.add(String.valueOf(c));
        }
        for (char c = '0'; c <= '9'; c++) {
            validCharacters.add(String.valueOf(c));
        }

        validCharacters.add(" ");
        validCharacters.add(".");
        validCharacters.add("-");
        validCharacters.add("_");
        validCharacters.add("(");
        validCharacters.add(")");
        validCharacters.add("[");
        validCharacters.add("]");
        validCharacters.add("{");
        validCharacters.add("}");
        validCharacters.add("~");
        validCharacters.add("<");
        validCharacters.add(">");
        validCharacters.add("@");
        validCharacters.add("&");
        validCharacters.add("#");
        validCharacters.add("%");
        validCharacters.add("$");
        validCharacters.add("!");
        validCharacters.add("'");
        validCharacters.add("+");
        validCharacters.add(",");
        validCharacters.add(";");
        validCharacters.add("`");
        validCharacters.add("^");
        validCharacters.add("=");
        validCharacters.add("~");
        return validCharacters;
    }

    public static long calculate(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }

    public enum Mode {
        NUMBER, ALL
    }
}