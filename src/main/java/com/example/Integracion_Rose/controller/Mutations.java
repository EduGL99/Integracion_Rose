package com.example.Integracion_Rose.controller;

import com.example.Integracion_Rose.exception.BadRequestException;
import com.example.Integracion_Rose.exception.InternalErrorException;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.example.Integracion_Rose.models.GetUnsafeOkHttpClient.getUnsafeOkHttpClient;

@RestController
@RequestMapping("/mutations")
public class Mutations {

    // crear proyecto nuevo - POSIBLE CAMBIO DE TEMPLATE
    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public String crearProject(String description,String typeState,String typePriority,String folioClass,String date,String typeFolio,
            String typeOrigen,String typeRegion,String severidadInicial,String typeAffectation,String typeAffectationClient,String typeClient,
            String rootTicket,String history,String historical,String elements,String user){

        String idElement = idElement(elements);
        String idUser = idUser(user);
        String name = "GI TroubleTicket";
        String type = "154618822677"; // id del tipo del template en uso en symphony
        try {
            JSONObject element = new JSONObject(idElement);
            JSONObject id=new JSONObject(idUser);

            elements = element.getJSONObject("data").getJSONObject("equipments").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getString("id");
            user = id.getJSONObject("data").getJSONObject("users").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getString("id");
            String creator = user;

            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation projectRose($input: AddProjectInput!) {\\r\\n  createProject(input: $input) {\\r\\n    id\\r\\n    createTime\\r\\n    name\\r\\n    createdBy {\\r\\n      email\\r\\n      id\\r\\n    }\\r\\n    location {\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    type {\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    priority\\r\\n    numberOfWorkOrders\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"type\":\""+type+"\",\"name\":\""+name+"\",\"description\":\""+description+"\",\"priority\":\""+typePriority+"\",\"creatorId\":\""+creator+"\",\"properties\":[{\"propertyTypeID\":\"163209270150\",\"stringValue\":\""+typeFolio+"\"},{\"propertyTypeID\":\"163209270151\",\"stringValue\":\""+typeState+"\"},{\"propertyTypeID\":\"163209270152\",\"stringValue\":\"\"},{\"propertyTypeID\":\"163209270153\",\"stringValue\":\""+date+"\"},{\"propertyTypeID\":\"163209270154\",\"stringValue\":\"\"},{\"propertyTypeID\":\"163209270155\",\"stringValue\":\""+folioClass+"\"},{\"propertyTypeID\":\"163209270156\",\"stringValue\":\""+typeOrigen+"\"},{\"propertyTypeID\":\"163209270157\",\"stringValue\":\""+typeRegion+"\"},{\"propertyTypeID\":\"163209270158\",\"stringValue\":\""+severidadInicial+"\"},{\"propertyTypeID\":\"163209270159\",\"stringValue\":\""+typeAffectation+"\"},{\"propertyTypeID\":\"163209270160\",\"stringValue\":\"\"},{\"propertyTypeID\":\"163209270161\",\"stringValue\":\"\"},{\"propertyTypeID\":\"163209270162\",\"stringValue\":\""+typeAffectationClient+"\"},{\"propertyTypeID\":\"163209270163\",\"stringValue\":\""+typeClient+"\"},{\"propertyTypeID\":\"163209270164\",\"stringValue\":\""+rootTicket+"\"},{\"propertyTypeID\":\"163209270165\",\"stringValue\":\""+history+"\"},{\"propertyTypeID\":\"163209270166\",\"stringValue\":\""+historical+"\"},{\"propertyTypeID\":\"163209270167\",\"nodeIDValue\":\""+elements+"\"},{\"propertyTypeID\":\"163209270168\",\"nodeIDValue\":\""+user+"\"}]}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AdaXo1wJmwq0XUNHX6xqGUe9KnaKK3few.s3shz3uF2wuKK%2BBPGrKaVfe7FYl5ODd0JEaHMI%2Bgdlg")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        } catch (Exception e){
            String error = "{\"500\":{\"description\":\"Internal Server Error\"}}";
            return error;
        }
    }

    //consultar id elemento
    public String idElement(String elements){
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "{\"query\":\"query EquipmentName(\\r\\n  $filters: [EquipmentFilterInput!]!\\r\\n) {\\r\\n  equipments(filterBy: $filters) {\\r\\n    edges {\\r\\n      node {\\r\\n        ...PowerSearchEquipmentResultsTable_equipment\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\\r\\n\\r\\nfragment PowerSearchEquipmentResultsTable_equipment on Equipment {\\r\\n  id\\r\\n  name\\r\\n  externalId\\r\\n  equipmentType {\\r\\n    id\\r\\n    name\\r\\n  }\\r\\n  properties{\\r\\n    id\\r\\n    rawValue\\r\\n  }\\r\\n  locationHierarchy {\\r\\n    id\\r\\n    name\\r\\n    locationType {\\r\\n      name\\r\\n      id\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{\"filters\":[{\"filterType\":\"EQUIP_INST_NAME\",\"operator\":\"CONTAINS\",\"stringValue\":\""+elements+"\"}]}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AqiZgUsUC2mR1ihqxE8PqUcOFf6kQlpJ_.tz63IVkzvk81wGCgZM7tE18Ca2H%2FQJ15s6P7isBSEMs")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            return null;
        }
    }

    //consultar id usuario
    public String idUser(String user){
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, "{\"query\":\"query UserName(\\r\\n  $filters: [UserFilterInput!]!\\r\\n) {\\r\\n  users(filterBy: $filters) {\\r\\n    edges {\\r\\n      node {\\r\\n        ...PowerSearchEquipmentResultsTable_equipment\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\\r\\n\\r\\nfragment PowerSearchEquipmentResultsTable_equipment on User {\\r\\n  id\\r\\n  name\\r\\n  email\\r\\n  role\\r\\n  groups{\\r\\n    id\\r\\n    name\\r\\n  }\\r\\n}\",\"variables\":{\"filters\":[{\"filterType\":\"USER_NAME\",\"operator\":\"CONTAINS\",\"stringValue\":\""+user+"\"}]}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AqiZgUsUC2mR1ihqxE8PqUcOFf6kQlpJ_.tz63IVkzvk81wGCgZM7tE18Ca2H%2FQJ15s6P7isBSEMs")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            return null;
        }
    }

    //actualizar proyecto - POSIBLE CAMBIO DE TEMPLATE
    public String editProject(String projectId,String name,String subestado,String severity,String rootTicket,String history,String historical){
        String type = "154618822677"; // id del tipo del template en uso en symphony
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation upproject($input: EditProjectInput!){\\r\\n  editProject(input: $input){\\r\\n    id\\r\\n    createTime\\r\\n    name\\r\\n    createdBy {\\r\\n      email\\r\\n      id\\r\\n    }\\r\\n    location {\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    type {\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    priority\\r\\n    numberOfWorkOrders\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"type\":\""+type+"\",\"id\":\""+projectId+"\",\"name\":\""+name+"\",\"properties\":[{\"propertyTypeID\":\"163209270152\",\"stringValue\":\""+subestado+"\"},{\"propertyTypeID\":\"163209270160\",\"stringValue\":\""+severity+"\"},{\"propertyTypeID\":\"163209270164\",\"stringValue\":\""+rootTicket+"\"},{\"propertyTypeID\":\"163209270165\",\"stringValue\":\""+history+"\"},{\"propertyTypeID\":\"163209270166\",\"stringValue\":\""+historical+"\"}]}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AqiZgUsUC2mR1ihqxE8PqUcOFf6kQlpJ_.tz63IVkzvk81wGCgZM7tE18Ca2H%2FQJ15s6P7isBSEMs")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            String error = "{\"500\":{\"description\":\"Internal Server Error\"}}";
            return error;
        }
    }

    //modificar WO elementos
    public String upWoElementos(String idWOE,String name,String projectId,String checklistElements){
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation upWOElemnt($input: EditWorkOrderInput!){ \\r\\n  editWorkOrder(input : $input){ \\r\\n  \\tid\\r\\n    name\\r\\n    workOrderType{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    description\\r\\n    status\\r\\n    priority\\r\\n    creationDate\\r\\n    comments{\\r\\n      author{\\r\\n        email\\r\\n      }\\r\\n      text\\r\\n    }\\r\\n    assignedTo{\\r\\n      email\\r\\n    }\\r\\n    properties{\\r\\n      propertyType{\\r\\n        name\\r\\n      }\\r\\n      stringValue\\r\\n    }\\r\\n    project{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    closeDate\\r\\n    checkListCategories{\\r\\n      title\\r\\n      checkList{\\r\\n        title\\r\\n        stringValue\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"id\":\""+idWOE+"\",\"name\":\""+name+"\",\"projectId\":\""+projectId+"\",\"checkListCategories\":[{\"title\":\"Elementos\",\"checkList\":"+checklistElements+"}]}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AqiZgUsUC2mR1ihqxE8PqUcOFf6kQlpJ_.tz63IVkzvk81wGCgZM7tE18Ca2H%2FQJ15s6P7isBSEMs")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            String error = "{\"404\":{\"description\":\"Not Found\"}}";
            return error;
        }
    }

    //crear WO alarmas
    @RequestMapping(value = "/woalarmas", method = RequestMethod.GET)
    public String woAlarmas(String projectId,String name,String priority,String checklistAlarmas){

        String typeWO = "236223201308";
        try {
            String description = "Bitácora: ";
            String status = "PLANNED";

            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation workorder($input: AddWorkOrderInput!){ \\r\\n  addWorkOrder(input : $input){ \\r\\n  \\tid\\r\\n    name\\r\\n    workOrderType{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    description\\r\\n    status\\r\\n    priority\\r\\n    creationDate\\r\\n    comments{\\r\\n      author{\\r\\n        email\\r\\n      }\\r\\n      text\\r\\n    }\\r\\n    assignedTo{\\r\\n      email\\r\\n    }\\r\\n    properties{\\r\\n      propertyType{\\r\\n        name\\r\\n      }\\r\\n      stringValue\\r\\n    }\\r\\n    project{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    closeDate\\r\\n    checkListCategories{\\r\\n      title\\r\\n      checkList{\\r\\n        title\\r\\n        stringValue\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"workOrderTypeId\":\""+typeWO+"\",\"name\":\""+name+"\",\"description\":\""+description+"\",\"status\":\""+status+"\",\"priority\":\""+priority+"\",\"projectId\":\""+projectId+"\",\"checkListCategories\":"+checklistAlarmas+"}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AqiZgUsUC2mR1ihqxE8PqUcOFf6kQlpJ_.tz63IVkzvk81wGCgZM7tE18Ca2H%2FQJ15s6P7isBSEMs")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            return null;
        }
    }

    //modificar WO alarmas
    public String upWoAlarmas(String idWOA,String name,String projectId,String checklistAlarmas){
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation upworkorder($input: EditWorkOrderInput!){ \\r\\n  editWorkOrder(input : $input){ \\r\\n  \\tid\\r\\n    name\\r\\n    workOrderType{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    description\\r\\n    status\\r\\n    priority\\r\\n    creationDate\\r\\n    comments{\\r\\n      author{\\r\\n        email\\r\\n      }\\r\\n      text\\r\\n    }\\r\\n    assignedTo{\\r\\n      email\\r\\n    }\\r\\n    properties{\\r\\n      propertyType{\\r\\n        name\\r\\n      }\\r\\n      stringValue\\r\\n    }\\r\\n    project{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    closeDate\\r\\n    checkListCategories{\\r\\n      title\\r\\n      checkList{\\r\\n        title\\r\\n        stringValue\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"id\":\""+idWOA+"\",\"name\":\""+name+"\",\"projectId\":\""+projectId+"\",\"checkListCategories\":"+checklistAlarmas+"}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AqiZgUsUC2mR1ihqxE8PqUcOFf6kQlpJ_.tz63IVkzvk81wGCgZM7tE18Ca2H%2FQJ15s6P7isBSEMs")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            String error = "{\"404\":{\"description\":\"Not Found\"}}";
            return error;
        }
    }

    //crear WO asignar folio
    public String woAsignar(String projectId,String name,String priority,String userAsig){

        String typeWO = "236223201307";
        String idUser = idUser(userAsig);
        try {
            JSONObject id=new JSONObject(idUser);

            String description = "Bitácora: ";
            String status = "PLANNED";
            String accion = "Asignar Folio";
            String notificationCloseId = "notificationCloseId";
            userAsig = id.getJSONObject("data").getJSONObject("users").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getString("id");

            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation workorderAsignar($input: AddWorkOrderInput!){ \\r\\n  addWorkOrder(input : $input){ \\r\\n  \\tid\\r\\n    name\\r\\n    workOrderType{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    description\\r\\n    status\\r\\n    priority\\r\\n    creationDate\\r\\n    comments{\\r\\n      author{\\r\\n        email\\r\\n      }\\r\\n      text\\r\\n    }\\r\\n    assignedTo{\\r\\n      email\\r\\n    }\\r\\n    properties{\\r\\n      propertyType{\\r\\n        name\\r\\n      }\\r\\n      stringValue\\r\\n      nodeValue{\\r\\n        name\\r\\n      }\\r\\n    }\\r\\n    project{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    closeDate\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"workOrderTypeId\":\""+typeWO+"\",\"name\":\""+name+"\",\"description\":\""+description+"\",\"status\":\""+status+"\",\"priority\":\""+priority+"\",\"projectId\":\""+projectId+"\",\"properties\":[{\"propertyTypeID\":\"163209242537\",\"stringValue\":\"\"},{\"propertyTypeID\":\"163209242538\",\"stringValue\":\""+notificationCloseId+"\"},{\"propertyTypeID\":\"163209242539\",\"stringValue\":\"\"},{\"propertyTypeID\":\"163209242540\",\"stringValue\":\"\"},{\"propertyTypeID\":\"163209261055\",\"stringValue\":\""+accion+"\"},{\"propertyTypeID\":\"163209267324\",\"nodeIDValue\":\""+userAsig+"\"}]}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AqiZgUsUC2mR1ihqxE8PqUcOFf6kQlpJ_.tz63IVkzvk81wGCgZM7tE18Ca2H%2FQJ15s6P7isBSEMs")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            return null;
        }
    }

    //modificar WO asignar folio
    public  String upWOAsignar(String idWOAs, String name,String projectId, String userAsig){

        String idUser = idUser(userAsig);
        try {
            JSONObject id=new JSONObject(idUser);

            String accion = "Asignar Folio";
            userAsig = id.getJSONObject("data").getJSONObject("users").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getString("id");

            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation upWOAsignar($input: EditWorkOrderInput!){ \\r\\n  editWorkOrder(input : $input){ \\r\\n  \\tid\\r\\n    name\\r\\n    workOrderType{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    description\\r\\n    status\\r\\n    priority\\r\\n    creationDate\\r\\n    comments{\\r\\n      author{\\r\\n        email\\r\\n      }\\r\\n      text\\r\\n    }\\r\\n    assignedTo{\\r\\n      id\\r\\n      email\\r\\n    }\\r\\n    properties{\\r\\n      propertyType{\\r\\n        name\\r\\n      }\\r\\n      stringValue\\r\\n      nodeValue{\\r\\n        name\\r\\n      }\\r\\n    }\\r\\n    project{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    closeDate\\r\\n    checkListCategories{\\r\\n      title\\r\\n      checkList{\\r\\n        title\\r\\n        stringValue\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"id\":\""+idWOAs+"\",\"name\":\""+name+"\",\"projectId\":\""+projectId+"\",\"properties\":[{\"propertyTypeID\":\"163209261055\",\"stringValue\":\""+accion+"\"},{\"propertyTypeID\":\"163209267324\",\"nodeIDValue\":\""+userAsig+"\"}]}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AqiZgUsUC2mR1ihqxE8PqUcOFf6kQlpJ_.tz63IVkzvk81wGCgZM7tE18Ca2H%2FQJ15s6P7isBSEMs")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            String error = "{\"404\":{\"description\":\"Not Found\"}}";
            return error;
        }
    }

    //crear WO solucionar incidencia ---
    public String woSolIncidencia(String name,String description,String priority,String projectId,String accion/*,String user*/){

        String typeWO = "236223201312";
        String notificationCloseId = "notificationCloseId";
        String status = "PLANNED";
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      //{\"propertyTypeID\":\"163209242606\",\"nodeIDValue\":\""+user+"\"}
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation workorderSolucion($input: AddWorkOrderInput!){ \\r\\n  addWorkOrder(input : $input){ \\r\\n  \\tid\\r\\n    name\\r\\n    workOrderType{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    description\\r\\n    status\\r\\n    priority\\r\\n    creationDate\\r\\n    comments{\\r\\n      author{\\r\\n        email\\r\\n      }\\r\\n      text\\r\\n    }\\r\\n    assignedTo{\\r\\n      email\\r\\n    }\\r\\n    properties{\\r\\n      propertyType{\\r\\n        name\\r\\n      }\\r\\n      stringValue\\r\\n      nodeValue{\\r\\n        name\\r\\n      }\\r\\n    }\\r\\n    project{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    closeDate\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"workOrderTypeId\":\""+typeWO+"\",\"name\":\""+name+"\",\"description\":\"Bitácora: "+description+"\",\"status\":\""+status+"\",\"priority\":\""+priority+"\",\"projectId\":\""+projectId+"\",\"properties\":[{\"propertyTypeID\":\"163209242605\",\"stringValue\":\""+accion+"\"},{\"propertyTypeID\":\"163209242607\",\"stringValue\":\"\"},{\"propertyTypeID\":\"163209242608\",\"stringValue\":\"\"},{\"propertyTypeID\":\"163209242610\",\"stringValue\":\""+notificationCloseId+"\"}]}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            return null;
        }
    }

    //modificar WO solucionar incidencia ---
    public String upWOSolIncidencia(String idWOSol,String name,String projectId,String statusIn){
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation upWoSolucion($input: EditWorkOrderInput!){ \\r\\n  editWorkOrder(input : $input){ \\r\\n  \\tid\\r\\n    name\\r\\n    workOrderType{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    description\\r\\n    status\\r\\n    priority\\r\\n    creationDate\\r\\n    comments{\\r\\n      author{\\r\\n        email\\r\\n      }\\r\\n      text\\r\\n    }\\r\\n    assignedTo{\\r\\n      email\\r\\n    }\\r\\n    properties{\\r\\n      propertyType{\\r\\n        name\\r\\n      }\\r\\n      stringValue\\r\\n      nodeValue{\\r\\n        name\\r\\n      }\\r\\n    }\\r\\n    project{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    closeDate\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"id\":\""+idWOSol+"\",\"name\":\""+name+"\",\"projectId\":\""+projectId+"\",\"status\":\""+statusIn+"\"}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3AiykXZL3bPehAPp_cOPkFmnKKW1jnZZF7.HWbA14RfFkB1HbxylTphVe%2B82WAc1t5tAZc47zJnXf0")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            String error = "{\"404\":{\"description\":\"Not Found\"}}";
            return error;
        }
    }

    //modificar WO asignar folio para cierre ---
    public String upWOAsignarToCierre(String idWOAs, String name,String projectId,String status){
        try {
            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation upWOAsignar($input: EditWorkOrderInput!){ \\r\\n  editWorkOrder(input : $input){ \\r\\n  \\tid\\r\\n    name\\r\\n    workOrderType{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    description\\r\\n    status\\r\\n    priority\\r\\n    creationDate\\r\\n    comments{\\r\\n      author{\\r\\n        email\\r\\n      }\\r\\n      text\\r\\n    }\\r\\n    assignedTo{\\r\\n      email\\r\\n    }\\r\\n    properties{\\r\\n      propertyType{\\r\\n        name\\r\\n      }\\r\\n      stringValue\\r\\n      nodeValue{\\r\\n        name\\r\\n      }\\r\\n    }\\r\\n    project{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    closeDate\\r\\n    checkListCategories{\\r\\n      title\\r\\n      checkList{\\r\\n        title\\r\\n        stringValue\\r\\n      }\\r\\n    }\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"id\":\""+idWOAs+"\",\"name\":\""+name+"\",\"projectId\":\""+projectId+"\",\"status\":\""+status+"\"}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            String error = "{\"404\":{\"description\":\"Not Found\"}}";
            return error;
        }
    }

    //crear WO validar cierre ---
    public String woValidar(String projectId,String name,String priority,String accion,String estado,String fechaFinFalla,String elementSolucion){

        String typeWO = "236223201313";
        String notificationCloseId = "notificationCloseId";
        String idElement = idElement(elementSolucion);
        try {
            JSONObject element = new JSONObject(idElement);
            elementSolucion = element.getJSONObject("data").getJSONObject("equipments").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getString("id");

            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation workorderValidar($input: AddWorkOrderInput!){ \\r\\n  addWorkOrder(input : $input){ \\r\\n  \\tid\\r\\n    name\\r\\n    workOrderType{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    description\\r\\n    status\\r\\n    priority\\r\\n    creationDate\\r\\n    comments{\\r\\n      author{\\r\\n        email\\r\\n      }\\r\\n      text\\r\\n    }\\r\\n    assignedTo{\\r\\n      email\\r\\n    }\\r\\n    properties{\\r\\n      propertyType{\\r\\n        name\\r\\n      }\\r\\n      stringValue\\r\\n      nodeValue{\\r\\n        name\\r\\n      }\\r\\n    }\\r\\n    project{\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    closeDate\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"workOrderTypeId\":\""+typeWO+"\",\"name\":\""+name+"\",\"description\":\"Bitácora: \",\"status\":\""+estado+"\",\"priority\":\""+priority+"\",\"projectId\":\""+projectId+"\",\"properties\":[{\"propertyTypeID\":\"163209242613\",\"stringValue\":\""+accion+"\"},{\"propertyTypeID\":\"163209272302\",\"stringValue\":\""+fechaFinFalla+"\"},{\"propertyTypeID\":\"163209242626\",\"nodeIDValue\":\""+elementSolucion+"\"},{\"propertyTypeID\":\"163209242629\",\"stringValue\":\""+notificationCloseId+"\"}]}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            return null;
        }
    }

    //modificar project para cerrar ---  - POSIBLE CAMBIO DE TEMPLATE
    public String cerrarProject(String projectId,String name,String typeState,String history,String elementSolucion,
                                String user,String cancelData, String subestado){

        String idUser = idUser(user);
        String type = "154618822677"; // id del tipo del template en uso en symphony

        try {
            // usuario resolutor
            JSONObject id=new JSONObject(idUser);
            user = id.getJSONObject("data").getJSONObject("users").getJSONArray("edges").getJSONObject(0).getJSONObject("node").getString("id");

            OkHttpClient client = getUnsafeOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation upproject($input: EditProjectInput!){\\r\\n  editProject(input: $input){\\r\\n    id\\r\\n    createTime\\r\\n    name\\r\\n    createdBy {\\r\\n      email\\r\\n      id\\r\\n    }\\r\\n    location {\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    type {\\r\\n      id\\r\\n      name\\r\\n    }\\r\\n    priority\\r\\n    numberOfWorkOrders\\r\\n  }\\r\\n}\",\"variables\":{\"input\":{\"type\":\""+type+"\",\"id\":\""+projectId+"\",\"name\":\""+name+"\",\"properties\":[{\"propertyTypeID\":\"163209270151\",\"stringValue\":\""+typeState+"\"},{\"propertyTypeID\":\"163209270152\",\"stringValue\":\""+subestado+"\"},{\"propertyTypeID\":\"163209270154\",\"stringValue\":\""+cancelData+"\"},{\"propertyTypeID\":\"163209270165\",\"stringValue\":\""+history+"\"},{\"propertyTypeID\":\"163209270179\",\"stringValue\":\""+elementSolucion+"\"},{\"propertyTypeID\":\"163209270192\",\"nodeIDValue\":\""+user+"\"}]}}}");
            Request request = new Request.Builder()
                    .url("https://symphony.telefonicamoviles.com.mx/graph/query")
                    .method("POST", body)
                    .addHeader("Authorization", "Basic am9zZWVkdWFyZG8uZ2FyY2lhbHVuYUBudHRkYXRhLmNvbTo2OTY5Njk2OTY5")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Cookie", "connect.sid=s%3Au4jTVlY4nVFlkUHjHH62xj0QjGahUawn.HhwI2q9C4ZmX7W39WeelpW4QiNngFfufK6EpkwF825o")
                    .build();
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body().string());
        }catch (Exception e){
            String error = "{\"500\":{\"description\":\"Internal Server Error\"}}";
            return error;
        }
    }

}
