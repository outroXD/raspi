package raspi.crypt;

import java.util.Optional;

public interface ICrypt {

    /**
     * 暗号化処理。
     * @param text 暗号化対象の文字列
     * @return 暗号化済み文字列
     */
    Optional<String> encrypt(String text);

    /**
     * 複合化処理。
     * @param text 複合化対象の文字列
     * @return 複合化済み文字列
     */
    Optional<String> decrypt(String text);
}
