/**
 * Copyright SDT. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bcia.julongchain.csp.gm.sdt.sm4;

import org.bcia.julongchain.csp.gm.sdt.sm3.SM3;
import org.bcia.julongchain.csp.intfs.IKey;

/**
 * GM SM4密钥
 *
 * @author tengxiumin
 * @date 2018/05/08
 * @company SDT
 */
public class SM4Key implements IKey {

    private SM3 sm3;
    private byte[] sm4Key;

    public SM4Key(byte[] sm4Key) {
        this.sm3 = new SM3();
        this.sm4Key = sm4Key;
    }

    /**
     * 获取密钥数据
     * @return 密钥数据
     */
    @Override
    public byte[] toBytes() {
        return this.sm4Key;
    }

    /**
     * 获取密钥标识
     * @return 密钥标识
     */
    @Override
    public byte[] ski() {
        try {
            return sm3.hash(this.sm4Key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 是否为对称密钥
     * @return true/false
     */
    @Override
    public boolean isSymmetric() {
        return true;
    }

    /**
     * 是否为私钥
     * @return true/false
     */
    @Override
    public boolean isPrivate() {
        return false;
    }

    /**
     * 获取公钥
     * @return 公钥
     */
    @Override
    public IKey getPublicKey() {
        return null;
    }
}
