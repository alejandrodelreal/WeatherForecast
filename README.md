# Como ejecutar el código
El codigo se puede probar desde swagger en local con la url: http://localhost:8080/swagger-ui.html

# Explicación
1. Empiezo extrayendo la condición before para poder establecer los tests apropiados (casos límite, en este caso dias 6 y 7) y poder refactorizar sin miedo a romper algo que funcionase antes de refactorizar.
Es de hecho aqui donde cambiando Date por Localdate tengo un percance y gracias a los tests es facil de corregir y no ha habido problemas.
2. Luego empiezo a extraer los DTOs de Metaweather API.
3. Refactorizo el codigo que hace peticiones a Metaweather API.
Paso la logica a un cliente y la logica mas genérica a dos interfaces que implementa el cliente.
Este paso de generar dos interfaces para estas funcionalidades podría ser discutido, tambien podrían generarse dos clases auxiliares. En esta ocasión me decanto por el uso de las interfaces para mostrar los default de Java 8 y porque a mi parecer se pueden interpretar como capacidades. Siendo interfaz además podemos hacer una "falsa" herencia múltiple
4. Genero un servicio intermedio que haga de intermediario entre el cliente y el resto de servicios. Podría describirse como un wrapper del cliente.
5. Empiezo a hacer mas test, intentando mockear todo lo que sean llamadas al API. Pues los tests no debería depender de servicios externos. El wrapper del cliente viene perfecto para mockear sin problemas. En mi humilde opinión, los tests tienen que ser siempre lo mas sencillo para hacerlos entendibles y mantenibles. Por eso me ha parecido mas correcto mockear las respuestas de lo que no sea responsabilidad del servicio, y no generar respuestas JSON, paresearlas etc... no es el cometido de los tests unitarios.
6. He generado test con EqualsVerifier para los DTO. Se podrían hacer mas completos comparando json o xml si fuera necesario. Aqui hay gente que es de la opinión que los DTO no hay que testearlos, en mi opinión los DTO habría que testearlos siempre y cuando puedas romper la integración con otro cliente (sobre todo en XML mas que JSON)
7. He migrado a SpringBoot creando un controlador. Quiero dejar claro que "no" he creado un API Rest porque no me ha parecido la naturaleza del problema, es mas bien un API RPC
8. Se pasan los servicios a Springboot para delegar en el servidor de aplicaciones el manejo de la pool de las instancias de estos.
9. Documentación con swagger: Normalmente pienso que todo proyecto tiene que quedar lo mas auto-documentado posible en el código, bien por usar y desgranar correctamente los nombres de los métodos, por lo que normalmente no me suele gustar documentar en Javadoc porque mi experiencia me dice que luego, con el tiempo, no se mantiene bien actualizado