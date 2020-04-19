import java.lang.reflect.Field;

/***
 * JDK 1.8以后会报错
 */
public class TestStrng {

    public static void main(String[] args) {
        String str = "ABCD";
        System.out.println("str = " + str);
        System.out.println("hashCode = " + str.hashCode());
        try {
            Field valueField = String.class.getDeclaredField("value");
            //value域是final private的，这里设置可访问
            valueField.setAccessible(true);
            char[] valueCharArr = (char[]) valueField.get(str);
            valueCharArr[0] = 'G';
            //此处输出第一组结果
            System.out.println("str = " + str);
            System.out.println("hashCode = " + str.hashCode());
            valueField.set(str, new char[]{'1', '2'});
            //此处输出第二组结果
            System.out.println("str = " + str);
            System.out.println("hashCode = " + str.hashCode());
        } catch (NoSuchFieldException | SecurityException
                | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}