package kero.javautil.commons.validate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ValidateCore {

  /**
   * 必須チェック(完全チェック).
   *
   * 必須キーがuserParamListtの中に存在するかを確認する。<BR>
   * 大文字・小文字は区別しない。<BR>
   * 完全一致として、部分一致はNGとする。(falseを返す)<BR>
   * 重複は許可する<BR>
   * 例) Listは「AA,AB,ABC」の場合に、必須キーの値とリターンを記載する。<BR>
   * ・「ab」→true。(大文字・小文字は区別しない)<BR>
   * ・「A」→false。（部分一致はNG）<BR>
   * ・「AC」→false。（部分一致はNG）<BR>
   *
   * @param key 必須キー
   * @param userParamList 必須キーが指定されているかを確認するユーザーが入力したのList
   * @return 必須キーが存在する場合はtrue,存在しない場合はfalse
   */
  static boolean requiredCheck(String key, List<String> userParamList) {
    try (Stream<String> stream = userParamList.stream()) {
      return stream.anyMatch(s -> s.equalsIgnoreCase(key));
    }
  }

  /**
   * 必須チェック(完全チェック).
   *
   * すべての必須キーがuserParamListの中に存在するかを確認する。<BR>
   *
   * @param keyList 必須キーのリスト
   * @param userParamList 必須キーが指定されているかを確認するユーザーが入力したのList
   * @return すべての必須キーが登録されている場合はtrue,それ以外はfalse
   */
  public static boolean requiredCheck(ArrayList<String> keyList, List<String> userParamList) {

    for (String key : keyList) {
      if (!requiredCheck(key, userParamList)) {
        return false;
      }
    }
    return true;
  }
}
