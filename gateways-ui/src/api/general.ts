import axios, { AxiosResponse } from "axios";
import { ErrorDto } from "../type/general-types";

export const BASE_API_URL = "http://localhost:8080/api";

export async function callGet<T>(
  url: string,
  params: Record<string, any> | null = null
) {
  return await axios.get<T>(url, {
    params: params,
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
  });
}

export async function callPost(
  url: string,
  body: any,
  params: Record<string, any> | null = null
): Promise<ErrorDto | null> {
    try {
        return (await axios.post<ErrorDto | null>(url, body, {
            headers: {
              "Content-Type": "application/json",
              Accept: "application/json",
            },
            params,
          })).data;
    } catch (e) {
        return Promise.resolve(e.response.data as ErrorDto);
    }
}

export async function callPatch<T = void>(url: string, body: any) {
  return await axios.patch<T>(url, body, {
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
  });
}

export async function callDelete<T = void>(url: string, body: any = null) {
  return await axios.delete<T>(url, {
    data: body,
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
  });
}
