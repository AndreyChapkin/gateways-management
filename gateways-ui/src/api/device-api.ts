import { DeviceDto } from "../type/device-types";
import { BASE_API_URL, callDelete, callGet, callPost } from "./general";

const DEVICE_BASE_API_URL = `${BASE_API_URL}/device`;

export async function createDevice(gatewayId: number, DeviceDto: DeviceDto) {
    const url = DEVICE_BASE_API_URL;
	await callPost(url, DeviceDto, {gatewayId});
}

export async function findDevice(id: number): Promise<DeviceDto> {
    const url = `${DEVICE_BASE_API_URL}/${id}`;
	const result = await callGet<DeviceDto>(url);
    return result.data;
}

export async function getAllDevices(): Promise<DeviceDto[]> {
    const url = DEVICE_BASE_API_URL;
	const result = await callGet<DeviceDto[]>(url);
    return result.data;
}

export async function deleteDevice(id: number): Promise<void> {
    const url = `${DEVICE_BASE_API_URL}/${id}`;
	await callDelete<void>(url);
}