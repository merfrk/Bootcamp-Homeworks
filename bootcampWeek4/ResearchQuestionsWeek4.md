# The Practical Test Pyramid Summary

### Top

  Test pyramid yazılım testlerini gruplandırmamızı söyleyen bir metafordur. Ürün production'a çıkmadan önce test edilmelidir. Ekipler bu noktada testleri otomatize etmeye yöneldi. 
Bu sayede yazılımın bozulup bozulmadığı günler ve haftalar yerine saniyeler sürmeye başladı. Test otomasyonu agile geliştirme, CI/CD ve DevOps kültürüyle el ele gider.
Etkili bir yazılım testi yaklaşımına sahip olmak, ekiplerin hızlı ve güvenle hareket etmelerini sağlar.

### The Importance of (Test) Automation

  Derlemeden testlere, dağıtıma ve altyapıya kadar her şeyi otomatikleştirmek, ileriye dönük tek yoldur. Geleneksel olarak yazılım testi, uygulamanızı bir test ortamına dağıtarak ve ardından bazı black-box stili testleri gerçekleştirerek yapılır.
Tüm değişiklikleri manuel olarak test etmek sıkıcı ve zaman alıcıdır. Ayrıca hatalara yol açar. Neysek tekrarlayan görevler için bir çare var: otomasyon.
Eğer büyük ölçekli bir yeniden düzenleme yapacaksanız ve testleri yazılmamışsa vay halinize. Bir şeyleri patlattınız mı diye teker teker test etmek zorunda kalırsınız.

### The Test Pyramid

  Otomatize testler konusunda bilmeniz gereken bir kavram vardır: test piramidi. Farklı test katmanları hakkında düşünmenizi söyleyen harika bir görsel metafor. Ayrıca her katmanda ne kadar test yapmanız gerektiğini de söyler.

![](https://martinfowler.com/articles/practical-test-pyramid/testPyramid.png)

Mike Cohn'un orijinal test piramidi, test takımınızın (aşağıdan yukarıya) içermesi gereken üç katmandan oluşur:
- Unit Tests
- Service Tests
- User Interface Tests

Modern bir bakış açısından, test piramidi aşırı derecede basit görünüyor ve bu nedenle yanıltıcı olabilir. Yine de Aşağıdaki konseptleri benimsemek iyi olacaktır:
- Farklı ayrıntı düzeyine sahip testler yazın.
- Ne kadar yüksek seviye alırsanız, o kadar az test yaptırmanız gerekir.

  Bakımı kolay bir test paketi bulmak için piramit şekline bağlı kalın. Çok fazla Unit Test yazın. e2e testler ve çok az sayıda üst düzey test yazın.
React ve angular gibi single page application frameworklerinin olduğu günlerde UI testlerinin piramidinizin en üst seviyesinde olması gerekmediği açıkça ortaya çıkıyor.
Codebase'inizle ve takımınızın tartışmalarıyla tutarlı olacak şekilde test katmanlarına farklı adlar verebilirsiniz.

### The Sample Application

Test piramidinin farklı katmanları için testler içeren bir test takımı içeren basit bir mikro hizmet yazılmış. 

#### Functionality

Uygulama 3 tane endpoint sağlıyor.


GET /hello -> "Hello World" döndürüyor.

GET /hello/{lastname} -> Verilen soy ada sahip kişi biliniyorsa "Hello isim soyisim" döndürür.

GET /weather -> Mevcut hava durumunu döndürür.

#### High-level Structure

sistemin yapısı aşağıdaki gibidir:

![](https://martinfowler.com/articles/practical-test-pyramid/testService.png)

#### Internal Architecture

Dahili olarak, Spring Service, Spring'e özgü bir mimariye sahiptir:

![](https://martinfowler.com/articles/practical-test-pyramid/testArchitecture.png)

Uygulama basit olduğu için service katmanı eklenmemiş. Uygulama için Spring Data kullanmış.

### Unit tests

  Testlerin temeli unit testlerden oluşacaktır. Unit test, kod tabanınızın belirli bir biriminin (test edilen konunuz) amaçlandığı gibi çalıştığından emin olur. 
Unit testler kapsam olarak en dar kapsama sahiptir ve sayıca diğer test türlerinden daha fazla olacaktır.

#### What's a Unit?

  Unit ten kastın ne olduğu çok kesin değildir. İşlevsel bir dilde çalışıyorsanız, bir birim büyük olasılıkla tek bir işlev olacaktır. Nesne yönelimli bir dilde bir birim, tek bir yöntemden tüm bir sınıfa kadar değişebilir.

#### Sociable and Solitary

  Solitary mi Social Unit Test mi yapacağınıza karar vermek önemli değil. Ö nemli olan testlerin otomatize edilmesidir. Gerçek işbirlikçileri kullanmak garip hale gelirse mock ve stub ları rahatça kullanabiliriz. Gerçek işbirlikçiyi dahil etmek  bir testte daha fazla güven veriyorsa, hizmetin yalnızca en dış kısımlarını kullanabiliriz.

#### Mocking and Stubbing

  Mock ve stublar iki farklı türde test ikilileridir. Testte size yardımcı olan bir uygulama ile üretimde kullanacağınız nesneleri değiştirmek için test çiftlerini kullanabilirsiniz. Basit bir deyişle, gerçek bir şeyi o şeyin sahte bir versiyonuyla değiştirdiğiniz anlamına gelir. 
Sahte sürüm gerçek gibi görünür ve davranır ancak birim testinizin başında kendi tanımladığınız hazır yanıtlarla yanıtlar.

  Test çiftlerinin kullanılması, Unit teste özgü değildir. Sisteminizin tüm parçalarını kontrollü bir şekilde simüle etmek için daha ayrıntılı test çiftleri kullanılabilir.
  
  Teknoloji seçiminiz ne olursa olsun, dilinizin standart library'si veya bazı popüler third party library'lerin size denemeler oluşturmanın zarif yollarını sağlama olasılığı yüksektir. Unit testler çok hızlı çalışacaktır. Birkaç dakika içinde binlerce birim test çalıştırabilirsiniz. Kod tabanınızın küçük parçalarını ayrı ayrı test edin ve testlerinizi hızlı tutmak için veritabanlarına, dosya sistemine veya HTTP sorgularını başlatmaktan (bu parçalar için sahte ve taslaklar kullanarak) kaçının.
  
  Unit testleri yazmayı bir kez öğrendikten sonra, onları yazarken daha akıcı hale geleceksiniz. Test Odaklı Geliştirmeye bakın ve birim testlerinizin geliştirmenize rehberlik etmesine izin verin. Bu harika bir akışa girmenize ve iyi ve bakımı kolay bir tasarım oluşturmanıza yardımcı olabilir.
  
#### What to Test?

  Birim testlerinin iyi yanı tüm üretim kodu sınıflarınız için yazabilmenizdir. 
    
  Bir Unit test sınıfı en azından sınıfın genel arabirimini test etmelidir. Özel yöntemler zaten test edilemez çünkü onları farklı bir test sınıfından çağıramazsınız. Birim testleri yazarken önemsiz olmayan tüm kod yollarınızın test edilmesini sağlamalıdırlar. Aynı zamanda, uygulamanıza çok yakından bağlı olmamalıdırlar çünkü refactoring yaptığınızda birim testleriniz patlar. Bu yüzden Unit testlerinizde dahili kod yapısını yansıtmayın. Örnek olarak ben x ve ye değerlerini girdiğimde sonuç z oluyor mu diye test etmektense, ben x ve y girdiğimde metod önce A sınıfını sonra B sınıfını çağırıp A sınıfıyla B sınıfının toplamını return ediyor mu şeklinde test etmek daha doğrudur.
  
  Private metodlar genellikle bir uygulama detayı olarak düşünülmelidir. Bu yüzden onları test etmeye yeltenmemelisiniz. 
  
  Yüksek bir test coverage elde etmek için tüm yöntemlerinizi test etmeniz gerektiğinde unit test yazmanın anlamsız hale geldiğini savunanlarda var. Genellikle test coverage'ı %100 görmek isteyen patronları olduğu için getter setter lara bile test yazmak zorunda kaldıklarından bahsederler. Bu kadarı da fazla ve saçma.
  
#### Test Structure

  Tüm testler için güzel bir yapı:
  
  1. Test verilerini ayarlayın
  2. Metodunuzu test altında çağırın
  3. Beklenen sonuçların döndürüldüğünü iddia edin

  "Arrange, Act, Assert" yani "Düzenle, Harekete Geç, İddia Et". "given", "when", "then" burada given kurulumu, when yöntemin ne zaman çağrıldığını ve then onaylama bölümünü yansıtır. Bu model testlerinizin okunmasının kolay ve tutarlı kalmasını sağlar.
  
#### Implementing a Unit Test

  Java için de-facto standart test frameworkü olan JUnit'i kullanarak unit testlerini yazıyoruz. Testimiz için gerçek PersonRepository sınıfını bir taslak ile değiştirmek için Mockito kullanıyoruz. arrange, act, assert yapısını takiben iki unit test yazıyoruz - pozitif bir vaka ve aranan kişinin bulunamadığı bir vaka.

### Integration Tests

  Entegrasyon Testleri yardımcı olmak için vardır. Uygulamanızın dışında yaşayan tüm parçalarla uygulamanızın entegrasyonunu test ederler. Bir veritabanıyla entegrasyonu test ediyorsanız, testlerinizi çalıştırırken bir veritabanı çalıştırmanız gerekir. Entegrasyon testini daha dar bir şekilde ele almayı ve ayrı hizmetleri ve veritabanlarını test çiftleriyle değiştirerek her seferinde bir entegrasyon noktasını test etmeyi seviyorum. Bir veritabanı entegrasyon testi şöyle görünür:
  
  ![](https://martinfowler.com/articles/practical-test-pyramid/dbIntegrationTest.png)
  
  1. Bir veritabanı kur.
  2. Uygulamayı veritabanına bağla.
  3. Kodun içinde veri tabanına veri yazan bir işlevi tetikleyin.
  4. Uygulamanın yanıtı doğru şekilde ayrıştırabildiğini kontrol edin.

  Verileri serialize ya da deserialize ettiğinizde tüm kod parçaları için entegrasyon testleri yazın. Bu çok fazla olur.
  
  - Servislerin apilerine yapılan çağrılarda
  - Db den bir şey okuyup bir şey yazdığımızda
  - Başka uygulamaların apilerini çağırdığımızda

  Bu sınırlar etrafında entegrasyon testleri yazmak, bu harici ortak çalışanlara veri yazmanın ve onlardan veri okumanın sorunsuz çalışmasını sağlar.

  Test piramidi ile ilgili olarak entegrasyon testleri, birim testlerinizden daha üst düzeydedir. 
  
#### Database Integration

```
public interface PersonRepository extends CrudRepository<Person, String> {
    Optional<Person> findByLastName(String lastName);
}
```
Bir kişiyi(Person) veritabanına kaydeden ve soyadına göre bulan basit bir entegrasyon testi:
```
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryIntegrationTest {
    @Autowired
    private PersonRepository subject;

    @After
    public void tearDown() throws Exception {
        subject.deleteAll();
    }

    @Test
    public void shouldSaveAndFetchPerson() throws Exception {
        Person peter = new Person("Peter", "Pan");
        subject.save(peter);

        Optional<Person> maybePeter = subject.findByLastName("Pan");

        assertThat(maybePeter, is(Optional.of(peter)));
    }
}
```
#### Integration With Separate Services

  Mikro hizmetimiz, bir hava durumu REST API'si olan darksky.net ile konuşuyor. Elbette hizmetimizin istek gönderdiğinden ve yanıtları doğru bir şekilde pars ettiğinden emin olmak istiyoruz. Entegrasyon testlerimizi yaparken kendi sahte darksky sunucumuzu çalıştırarak gerçek darksky sunucularına çarpmaktan kaçınabiliriz. Bu kulağa çok büyük bir görev gibi gelebilir. Wiremock gibi araçlar sayesinde çok kolay.
  ```
  @RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherClientIntegrationTest {

    @Autowired
    private WeatherClient subject;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void shouldCallWeatherService() throws Exception {
        wireMockRule.stubFor(get(urlPathEqualTo("/some-test-api-key/53.5511,9.9937"))
                .willReturn(aResponse()
                        .withBody(FileLoader.read("classpath:weatherApiResponse.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        Optional<WeatherResponse> weatherResponse = subject.fetchWeather();

        Optional<WeatherResponse> expectedResponse = Optional.of(new WeatherResponse("Rain"));
        assertThat(weatherResponse, is(expectedResponse));
    }
}
  ```
  Wiremock'u kullanmak için, sabit bir bağlantı noktasında (8089) bir WireMockRule başlatırız. DSL'yi kullanarak Wiremock sunucusunu kurabilir, dinlemesi gereken uç noktaları tanımlayabilir ve yanıt vermesi gereken hazır yanıtları ayarlayabiliriz.

Ardından, test etmek istediğimiz yöntemi çağırırız, üçüncü taraf hizmeti çağırır ve sonucun doğru şekilde ayrıştırılıp ayrıştırılmadığını kontrol ederiz.

gerçek sunucu yerine sahte Wiremock sunucusunu çağırmak için src/test/resources içindeki application.properties dosyamıza aşağıdakini eklememiz lazım.
```
weather.url = http://localhost:8089
```
  Testlerimizde gerçek hava durumu API'sinin URL'sini sahte bir URL ile değiştirmek, URL'yi WeatherClient sınıfımızın yapıcısına enjekte ederek mümkün olur:
  
```
@Autowired
public WeatherClient(final RestTemplate restTemplate,
                     @Value("${weather.url}") final String weatherServiceUrl,
                     @Value("${weather.api_key}") final String weatherServiceApiKey) {
    this.restTemplate = restTemplate;
    this.weatherServiceUrl = weatherServiceUrl;
    this.weatherServiceApiKey = weatherServiceApiKey;
}
```
Bu şekilde WeatherClient'ımıza, uygulama özelliklerimizde tanımladığımız weather.url özelliğinden weatherUrl parametresinin değerini okumasını söyleriz. Kurduğumuz sahte sunucunun gerçek sunucu gibi davranmasını nasıl sağlayabiliriz? Sahte sunucuya ve gerçek sunucuya karşı sözleşme testleri yapmak, entegrasyon testlerimizde kullandığımız sahte öğenin sadık bir test çifti olmasını sağlar. 

### Contract Tests

  Daha modern yazılım geliştirme kuruluşları, bir sistemin gelişimini farklı ekipler arasında yayarak geliştirme çabalarını ölçeklendirmenin yollarını bulmuşlardır. Bireysel ekipler, birbirlerinin ayaklarına basmadan bireysel, loosely coupled hizmetler oluşturur ve bu hizmetleri büyük, uyumlu bir sisteme entegre eder. Sisteminizi birçok küçük hizmete bölmek, genellikle bu hizmetlerin belirli arabirimler aracılığıyla birbirleriyle iletişim kurması gerektiği anlamına gelir.

- REST and JSON via HTTPS
- RPC using something like gRPC
- Event-driven architecture using queues

![](https://martinfowler.com/articles/practical-test-pyramid/contract_tests.png)

  Tüketen ve sağlayan hizmetleri farklı ekipler arasında sıklıkla yayarken, kendinizi bu hizmetler arasındaki arabirimi (sözde sözleşme) açıkça belirtmeniz gereken bir durumda bulursunuz. Geleneksel olarak şirketler bu soruna şu şekilde yaklaşmışlardır:
  
  - Uzun ve ayrıntılı bir arayüz belirtimi yazın (sözleşme)
  - Belirlenen sözleşmeye göre sağlama hizmetini uygulamak
  - Arayüz spesifikasyonunu çitin üzerinden tüketen ekibe atın
  - Arayüzü tüketme konusundaki rollerini uygulayana kadar bekleyin
  - Her şeyin işe yarayıp yaramadığını görmek için büyük ölçekli manuel sistem testi yapın
  - Umarım her iki takım da sonsuza kadar arayüz tanımına sadık kalır ve işleri batırmaz.

  Daha modern yazılım geliştirme ekipleri otomatikleştirilmiş sözleşme testleri kullanırlar. Çevik bir organizasyonda, daha verimli ve daha az savurgan yolu seçmelisiniz. Uygulamalarınızı aynı organizasyon içinde oluşturursunuz. Çitin üzerinden aşırı ayrıntılı belgeler atmak yerine, diğer hizmetlerin geliştiricileriyle doğrudan konuşmak gerçekten çok zor olmamalı.
  
  Tüketiciye Dayalı Sözleşme testleri (CDC testleri), tüketicilerin bir sözleşmenin uygulanmasını yönlendirmesine izin verir. CDC kullanarak, bir arabirimin tüketicileri, arabirimden ihtiyaç duydukları tüm veriler için arabirimi kontrol eden testler yazar. Tüketim ekibi daha sonra bu testleri yayınlar, böylece yayınlama ekibi bu testleri kolayca alabilir ve yürütebilir. Sağlayıcı ekip artık CDC testlerini çalıştırarak API'lerini geliştirebilir.
  
  ![](https://martinfowler.com/articles/practical-test-pyramid/cdc_tests.png)
  
  Bu yaklaşım, sağlayıcı ekibin yalnızca gerçekten gerekli olanı uygulamasına olanak tanır. Testler yeşil kaldığı sürece, ekip diğer ekipler için endişelenmeden istedikleri değişiklikleri yapabilir. Tüketici Odaklı Sözleşme yaklaşımı, sizi şuna benzeyen bir süreçle baş başa bırakır:
  
  - Tüketim ekibi, tüm tüketici beklentileriyle otomatik testler yazar.
  - Sağlayıcı ekip için testleri yayınlarlar.
  - Sağlayıcı ekip, CDC testlerini sürekli olarak çalıştırır ve onları yeşil tutar.
  - CDC testleri bozulduğunda her iki takım da birbirleriyle konuşur.

#### Consumer Test (our team)

  Mikro hizmetimiz hava durumu API'sini kullanır. Bu nedenle, mikro hizmetimiz ile hava durumu hizmeti arasındaki sözleşmeye (API) ilişkin beklentilerimizi tanımlayan bir tüketici testi yazmak bizim sorumluluğumuzdur.
  
#### Provider Test (the other team)
  
  Sağlayıcı testi, hava durumu API'sini sağlayan kişiler tarafından uygulanmalıdır. darksky.net tarafından sağlanan genel bir API kullanıyoruz. Teorik olarak, darksky ekibi, uygulamaları ile hizmetimiz arasındaki sözleşmeyi ihlal etmediklerini kontrol etmek için sağlayıcı testini uygular.
  
#### Provider Test (our team)

  Hizmetimiz ile hava durumu sağlayıcısı arasındaki sözleşmenin nasıl test edileceğini gördük. Bu arayüz ile hizmetimiz tüketici, hava durumu hizmeti sağlayıcı olarak hareket eder. Biraz daha düşününce hizmetimizin başkaları için bir sağlayıcı görevi gördüğünü göreceğiz: Başkaları tarafından tüketilmeye hazır birkaç uç nokta sunan bir REST API sağlıyoruz.
  
### UI Tests

  Çoğu uygulamanın bir çeşit kullanıcı arayüzü vardır. Tipik olarak, web uygulamaları bağlamında bir web arayüzünden bahsediyoruz. İnsanlar genellikle bir REST API'sinin veya bir komut satırı arayüzünün, süslü bir web kullanıcı arayüzü kadar bir kullanıcı arayüzü olduğunu unutur.

UI testleri, uygulamanızın kullanıcı arayüzünün doğru çalıştığını test eder. Kullanıcı girişi doğru eylemleri tetiklemeli, veriler kullanıcıya sunulmalı, UI durumu beklendiği gibi değişmelidir.

![](https://martinfowler.com/articles/practical-test-pyramid/ui_tests.png)

### End-to-End Tests
  
  Dağıtılan uygulamanızı kullanıcı arabirimi aracılığıyla test etmek, uygulamanızı test etmenin en uçtan uca yoludur. Daha önce açıklanan, web sürücüsüne dayalı UI testleri, uçtan uca testlere iyi bir örnektir.
  
  ![](https://martinfowler.com/articles/practical-test-pyramid/e2etests.png)
  
#### User Interface End-to-End Test

  Uçtan uca testler için Selenium ve WebDriver protokolü birçok geliştirici için tercih edilen araçtır. Selenium ile beğendiğiniz bir tarayıcı seçebilir ve web sitenizi otomatik olarak aramasına izin verebilir, burayı ve burayı tıklayın, verileri girin ve kullanıcı arayüzünde değişiklik olup olmadığını kontrol edin.
  
#### REST API End-to-End Test
  
  Uygulamanızı test ederken bir grafik kullanıcı arabiriminden kaçınmak, uygulama yığınının geniş bir bölümünü kapsamaya devam ederken tam uçtan uca testlerden daha az kesintili testler bulmak için iyi bir fikir olabilir. Bu, uygulamanızın web arayüzü üzerinden test yapmak özellikle zor olduğunda kullanışlı olabilir. Belki bir web kullanıcı arayüzünüz bile yoktur, ancak bunun yerine bir REST API'si sunarsınız (çünkü bir yerde bu API ile konuşan tek bir sayfa uygulamanız vardır veya sadece güzel olan her şeyi hor gördüğünüz için).
  
### Conclusion
  
  Bu kadar! Bunun, yazılımınızı neden ve nasıl test etmeniz gerektiğini açıklamak için uzun ve zor bir okuma olduğunu biliyorum. Harika haber şu ki, bu bilgi oldukça zamansız ve ne tür bir yazılım oluşturduğunuzdan bağımsız. Bir mikro hizmet ortamı, IoT cihazları, mobil uygulamalar veya web uygulamaları üzerinde çalışıyor olmanız farketmez, bu makaledeki dersler bunların tümüne uygulanabilir.
  
---------------------------------------------------------------------

### Regression test nedir ? Kısaca açıklayınız.

  Regresyon testi canlıda çalışan kodun üzerinde yapılan değişikliklerin kontrolü için kullanılır. Bu değişiklikler yeni bir fonksiyon, hata çözümü ya da performans geliştirmesi olabilir. Regresyon testleri genellikle değişiklikler son aşamaya geldiğinde ve yazılımın yeni sürümü yayınlamadan önce gerçekleştirilir. Regresyon testlerinin öncelikli amacı, uygulamanın kritik alanlarının hala beklendiği gibi çalıştığını kontrol etmektedir. 
    
   Regresyon testleri:
   - Yazılımın değişiklik sonrasında son kalitesinin kontrol edilmesini
   - Daha önce çıkan hataların düzeldiğinin kontrolünü
   - Yazılım ekibinin ürün hakkında güveninin artmasını sağlar

### A/B test nedir ? Kısaca açıklayınız.
   
   A/B testinin amacı ikili karşılaştırmalarla farklı içerik ve tasarım örneklerini karşılaştırmayı ifade ediyor. Bu karşılaştırmanın amacı A/B testini uyguladığınız sisteme göre farklılık gösterebilir. Anasayfa, üyelik sayfası, satış sayfası, landing page ya da bülten için farklı A/B testleri uygulayabilir ve hedeflediğiniz eylemin(üye olma, satışa dönüş, tıklama..) değişimini takip edebilirsiniz.

  Farklı test örneklerine yönlendireceğiniz kullanıcıları trafik yönlendiren sitelere göre ayırabilirsiniz. Örneğin arama motorundan gelen ziyaretçileri bir örneğe diğer kullanıcıları farklı bir örneğe yönlendirebilirsiniz. Bülten örneklerinde ise manuel tercihler yapmanız gerekecektir.

![](https://cdn.webrazzi.com/uploads/2011/02/AB-Testi.jpg)

### Black box / white box test kavramlarını açıklayınız.
  
  Kara kutu testleri; kodun yapısı(structure), tasarımı(design) ve uygulanışı(implementation) ile ilgilenmez. Kara kutu testlerinde girdi ve çıktı değişimine göre sistemin nasıl çalıştığı test edilir. Kara kutu test çeşitleri çoğu yazılım test uzmanı tarafından yaygın olarak kullanılan test çeşitleridir. Beyaz kutu testinde, kodun içine girilerek kodun doğruluğu ve kalitesi test edilir. Bu test türünde kod erişimi zorunludur. Kod yapısı ve tasarımına yönelik testler gerçekleştirilir. Örneğin, gereksiz bir kod bloğu tespit edilebilir veya kodun okunulabilirliğini arttırmaya yönelik durumlar tespit edilebilir. Kodda erken bulunacak hatalar Kara Kutu(Black Box) testlerini de kolaylaştırmaktadır. Beyaz kutu testleri çoğunlukla geliştiriciler tarafından yapıldığı gibi test uzmanları tarafından da uygulanabilir.
  
### Mutation test nedir ? Kısaca açıklayınız.
   
   Mutasyon Testi, kaynak koddaki belirli ifadeleri değiştirdiğimiz(mutant) ve test senaryolarının hataları bulabildiğini kontrol ettiğimiz bir tür yazılım testidir. Temelde birim testi(unit test) için kullanılan bir beyaz kutu testi türüdür. Mutant programdaki değişiklikler son derece küçük tutulur, bu nedenle programın genel hedefini etkilemez. Mutasyon testinin amacı, mutant kodunu kaldıracak kadar sağlam olması gereken test vakalarının kalitesini değerlendirmektir. Bu yöntem, programda bir hata oluşturmayı içerdiği için hata tabanlı test stratejisi olarak da adlandırılır.
   
### Behavior Driven Development (BDD) nedir, neyi amaçlamaktadır ?
  
  Behavior Driven development (BDD), yazılım süreçlerinin daha test odaklı gitmesini sağlayan bir yaklaşımdır. Aynı zamanda müşteri ile aramızda yaşayan bir döküman oluşmasını sağlayabilir. BDD, Test Driven Development (TDD) gibi prensip olarak öncelikle test kodları yazılsın daha sonrasında proje kodu yazılsın anlayışını benimsemektedir.
  BDD’nin en güzel yanlarından biri konuşma dilinde test senaryoları yazmamıza olanak sağlamasıdır. İş analistleri müşteri ile yaptığı görüşmeler sonrasında ihtiyacı anlayarak user storyler oluşturmakta ve oluşturulan bu user storyler üzerinden de test senaryoları hazırlanarak koda dökülmektedir. Kısacası müşterinin ihtiyacı konuşma dilinde koda döküldüğü için müşteri ile ortak bir dilde buluşmaya olanak sağlayan bir yaklaşımdır aslında.
 
#### BDD’nin Avantajları

- İşbirliğini arttırır ve geliştirir. Konuşma dilinde test senaryoları yazıldığı için ekibe yeni katılan üyeler sürece çok çabuk adapte olabilir ve davranışsal senaryolar yazabilirler.
- Kodun kalitesini artırarak temelde bakım maliyetini düşürür ve proje riskleri en aza indirilir.
- Müşterinin ihtiyacına yönelik belirlenen user storyler aracılığıyla test senaryoları oluşturulup koda döküldüğü için belirlenen ihtiyaçlar daha iyi karşılanmaktadır.
- Geliştiriciler işleyişi daha iyi öngörebildikleri için yazdıkları koda daha fazla güvenirler.

### Agile test quadrant nedir ? Quadrant’ların kapsamını kısaca açıklayınız.

  Test Türlerinin iki yönünü birleştiren aşağıdaki Agile Test Quandrants Brian Marick tarafından türetilmiştir. 
  
  ![](https://www.tutorialspoint.com/agile_testing/images/quadrants.jpg)
  
  Agile Test Quadrantları ekiplerin ihtiyaç duyulan testi belirlemesine, planlamasına ve uygulamasına yardımcı olmak için yararlı bir sınıflandırma sağlar.
  
  - Quadrant Q1- Unit Level, Technology Facing ve geliştiricileri destekler. Birim testleri bu quadranta aittir. Bu testler Otomatik testler olabilir.
  - Quadrant Q2- Sistem düzeyi, işle ilgili durumlar ve ürün davranışına uygunluk. Fonksiyonel testler bu quadranta aittir. Bu testler manuel veya otomatiktir.
  - Quadrant Q3- Sistem veya Kullanıcı Kabul Düzeyi, İşe Yönelik ve gerçek zamanlı senaryolara odaklanın. Kullanıcı Kabul Testleri bu quadranta aittir. Bu testler manueldir.
  - Quadrant Q4- Sistem veya Operasyonel Kabul Düzeyi, Teknolojiyle Karşılaşma ve Performansa Odaklanma, Yük, Stres, Sürdürülebilirlik, Ölçeklenebilirlik Testleri. Otomasyon testi ile birlikte bu testler için özel araçlar kullanılabilir.

  Bunları birleştirerek, Hangi test Ne Zaman'ı yansıtan Agile Test Quadrantları aşağıdaki gibi görselleştirilebilir.
  
  ![](https://www.tutorialspoint.com/agile_testing/images/testing_quadrants.jpg)
  

