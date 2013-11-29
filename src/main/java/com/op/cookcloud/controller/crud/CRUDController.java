package com.op.cookcloud.controller.crud;

import javax.ws.rs.core.Response;

/**
 * User: alexander.pastukhov
 * Date: 11/28/13
 * Time: 7:42 PM
 */
public interface CRUDController<E> {

    Response read();

    void update(E e);

    void create(E e);

    void delete(Integer id);

}
