package com.cochintravels.until.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SheetJson {
    @Id
    private String sheetName;

    @Lob
    private String content;
}
