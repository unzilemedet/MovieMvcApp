package com.bilgeadam.entity;


import com.bilgeadam.utility.enums.EStatus;
import com.bilgeadam.utility.enums.EUserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;


@Entity //Bu sınıfın bir veritabanı sınıfı olduğunu ifade eder.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users") //Veritabanı tablo oluşturma işlemleri için gerekli değildir.
public class User {
    @Id //Bu anotasyonla id olarak property işaretlenir. Property'nin int olması şart değildir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Veritabanındaki int id'nin nasıl artacağını belirler
    private Integer id;
    @Column(length = 50) //persistence anotasyonudur, jdk kullanmanız yeterlidir
    //validation anotasyonudur, verinin içerisinin boş gönderilmemesini sağlar
    //@NotBlank //sayısal değerler için deneyiniz
    //@NotNull //vernini girilmesi zorunludur
    private String name;
    @Column(length = 50)
    //@Length //hibernate anotasyonu, temel olarak size ile aynı görevi görür. Min-max değerler verilebilir
    private String surname;
    @Email //Bu property'nin email formatta girilmesi gerektiğini anlatır.
    //Bir validation anotasyonudur, bağımlılık yüklenmezse kullanılamaz.
    //@Size(min = 10, max = 50, message = "Min 10 max 50 karakter bulunabilir")
    private String email;
    @Column(length = 15)
    //@Lob //persistence üyesidir. bir veri tipinin "bigdata" olduğunu gösterir. Max 2048 karakter(String)
    private String phone;
    @Column(length = 32)
    //Bir regex tanımlamak için @Pattern kullanılır. Genel olarak email, password gibi property'ler için kullanılır
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
            message = "Şifre en az bir büyük, bir küçük, harf, rakam, ve özel karakterden oluşmalıdır.")
    private String password;
    @Column(length = 32)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
            message = "Şifre en az bir büyük, bir küçük, harf, rakam, ve özel karakterden oluşmalıdır.")
    private String repassword;

    @ElementCollection //Bir sınıfın id' si başka bir sınıfta liste şeklinde tutulacaksa
    //bu liste olarak tutulan id ile tutulduğu sınıfın id' si dış bir tabloda oluşturulur.
    private List<Integer> genreId;
    @ElementCollection
    private List<Integer> movieId;
    @ElementCollection //OneToMany --> Veritabanı tablosunda bir tane userId' ye birden çok commentId eklenmelidir.
                        //userId primary key olduğu için bir tabloda birden fazla kez tanımlanamaz.
                        //Ama birden çok commentId verilmesi gerekmektedir. Bunun için userId ve commentId' den oluşan
                        //Bir ara tablo kurularak, bu ara tablodaki userId ile User tablosundaki userId foreign key olarak bağlanır.
    private List<Integer> commentId;

    @Enumerated(EnumType.STRING) //Bu enum'ın bir string olarak kullanılacağını belirtir.
    @Builder.Default //Bir property build edilirken default değerini belirler.
    private EStatus status = EStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EUserType userType = EUserType.USER;


    //Bir crud işleminin(save,update) kim tarafından ne zaman yapıldığını takip edip veritabanına ekleyen anotasyonlar
    //@CreatedBy --> ekleme işleminin kimin tarafından yapıldığını gösterir
    //@CreatedDate --> ekleme işleminin ne zaman yapıldığını gösterir
    //@LastModifiedBy --> en son değişikliğin kimin tarafından yapıldığını gösterir
    //@LastModifiedDate --> en son değişiklik yapılan tarihi gösterir
}
