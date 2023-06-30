import { AxiosResponse } from "axios";
import { GatewayDto } from "../type/gateway-types";
import { BASE_API_URL, callDelete, callGet, callPost } from "./general";
import { ErrorDto } from "../type/general-types";

const GATEWAY_BASE_API_URL = `${BASE_API_URL}/gateway`;

export async function createGateway(gatewayDto: GatewayDto): Promise<ErrorDto | null> {
    const url = GATEWAY_BASE_API_URL;
	return await callPost(url, gatewayDto);
}

export async function findGateway(id: number): Promise<GatewayDto> {
    const url = `${GATEWAY_BASE_API_URL}/${id}`;
	const result = await callGet<GatewayDto>(url);
    return result.data;
}

export async function getAllGateways(): Promise<GatewayDto[]> {
    const url = GATEWAY_BASE_API_URL;
	const result = await callGet<GatewayDto[]>(url);
    return result.data;
}

export async function deleteGateway(id: number): Promise<void> {
    const url = `${GATEWAY_BASE_API_URL}/${id}`;
	await callDelete<void>(url);
}