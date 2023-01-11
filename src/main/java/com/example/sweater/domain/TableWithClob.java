package com.example.sweater.domain;

import javax.persistence.*;

@Entity
@Table(name = "table_with_clob")
public class TableWithClob {
    @Id
    private Long id;

    @Lob
    @Column(name = "some_Clob")
    @Basic(fetch = FetchType.LAZY)
    private String someClob;
}
