Feature: Pruebas de la API
  @Test1
  Scenario: Listar usuarios
  Given que accedo al servicio de listar usuarios de ReqRes
  When realizo una solicitud GET al endpoint "/api/users?page=2"
  Then la respuesta debe contener la lista de usuarios en la página 2 y el estado de la respuesta debe ser 200
  @Test2
  Scenario: Crear un nuevo usuario
  Given que tengo los datos para crear un nuevo usuario
  When realizo una solicitud POST al endpoint "/api/users" con los datos del usuario
  Then la respuesta debe indicar que el usuario fue creado exitosamente con un estado de 201, y debe devolver el ID del usuario creado
  @Test3
  Scenario: Actualizar un usuario existente
  Given que tengo un ID de usuario existente
  When realizo una solicitud PUT al endpoint "/api/users/{id}" con datos actualizados
  Then la respuesta debe indicar que el usuario fue actualizado exitosamente con un estado de 200, y debe devolver los datos actualizados
  @Test4
  Scenario: Eliminar un usuario existente
  Given que tengo un ID de usuario existente
  When realizo una solicitud DELETE al endpoint "/api/users/{id}"
  Then la respuesta debe devolver un estado de 204, indicando que el usuario fue eliminado exitosamente
