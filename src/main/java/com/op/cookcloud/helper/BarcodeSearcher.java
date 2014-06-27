package com.op.cookcloud.helper;

import com.op.cookcloud.model.Product;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 09.11.13
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public interface BarcodeSearcher {
    public Product lookUpProductByEAN(String code);
    public Product lookUpProductByUPC(String code);
    public Product lookUpProduct(String code) throws IOException;

    }
