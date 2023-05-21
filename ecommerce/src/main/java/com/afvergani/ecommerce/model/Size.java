package com.afvergani.ecommerce.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Size {

    private int id;
    private int productId;
    private boolean backSoon;
    private boolean special;

}
