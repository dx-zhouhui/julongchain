/*
 * Copyright Dingxuan. All Rights Reserved.
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
package org.bcia.julongchain.core.common.smartcontractprovider;

import org.bcia.julongchain.common.exception.JulongChainException;
import org.bcia.julongchain.common.log.JulongChainLog;
import org.bcia.julongchain.common.log.JulongChainLogFactory;
import org.bcia.julongchain.protos.node.SmartContractPackage;

/**
 * 文件系统智能合约
 *
 * @author sunianle, sunzongyu
 * @date 5/11/18
 * @company Dingxuan
 */
public class SmartContractInfoFS implements ISmartContractCacheSupport {

    private static JulongChainLog log = JulongChainLogFactory.getLog(SmartContractInfoFS.class);

    /**
	 * 在文件系统中获取智能合约
     */
    @Override
    public ISmartContractPackage getSmartContract(String name, String version) {
        SDSPackage scsdsPackage = new SDSPackage();
        try {
            scsdsPackage.initFromFS(name, version);
        } catch (JulongChainException e) {
            log.info(e.getMessage(), e);
            log.info("Trying SignedSDSPackage");
            SignedSDSPackage sscsdsPackage= new SignedSDSPackage();
            try {
                sscsdsPackage.initFromFS(name, version);
            } catch (JulongChainException e1) {
                log.info(e1.getMessage(), e1);
                log.info("Can not init from fs");
                return null;
            }
            return sscsdsPackage;
        }
        return scsdsPackage;
    }

    /**
	 * 将智能合约写入文件系统
     */
    public ISmartContractPackage putSmartContract(SmartContractPackage.SmartContractDeploymentSpec deploymentSpec) throws JulongChainException {
        SDSPackage sdsPackage = new SDSPackage();
        sdsPackage.initFromBuffer(deploymentSpec.toByteArray());
        sdsPackage.putSmartcontractToFS();
        return sdsPackage;
    }


}
