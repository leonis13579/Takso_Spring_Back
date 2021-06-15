package ru.realityfamily.takso.Request;

import org.springframework.web.bind.annotation.PathVariable;
import retrofit2.Call;
import retrofit2.http.GET;
import ru.realityfamily.takso.Models.Rightech.RightechObject;

import java.util.List;

public interface RightechController {
    @GET("/api/v1/objects")
    public Call<List<RightechObject>> getObjects();

    @GET("/api/v1/objects/{objectId}")
    public Call<RightechObject> getObject(@PathVariable("objectId") String objectId);
}
