
import * as CryptoJS from 'crypto-js';

export class Crypt {

    static cryptAes(text: string) {
        const key = CryptoJS.enc.Utf8.parse('7061737323313233');
        const iv = CryptoJS.enc.Utf8.parse('7061737323313233');
        const encrypted = CryptoJS.AES.encrypt(CryptoJS.enc.Utf8.parse(text), key,
        {
            keySize: 128 / 8,
            iv: iv,
            model: CryptoJS.mode.CBC,
            padding: CryptoJS.pad.Pkcs7
        });

        console.log('encrypted text', encrypted.toString());
        return encrypted.toString();
    }

    static decryptAes(encryptedText) {
        const key = CryptoJS.enc.Utf8.parse('7061737323313233');
        const iv = CryptoJS.enc.Utf8.parse('7061737323313233');
        const decrypted = CryptoJS.AES.decrypt(encryptedText, key, {
            keySize: 128 / 8,
            iv: iv,
            mode: CryptoJS.mode.CBC,
            padding: CryptoJS.pad.Pkcs7
        });

        const decryptedText = decrypted.toString(CryptoJS.enc.Utf8);
        console.log('decrypted text', decryptedText);
        return decryptedText;
    }

}
