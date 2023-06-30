import { useCallback, useState } from "react";
import { deleteDevice } from "../../api/device-api";
import { GatewayDto } from "../../type/gateway-types";
import CreateDeviceForm from "./component/CreateDeviceForm";
import styles from "./GatewayDetails.module.scss";

export interface GatewayDetailsProps {
  gateway: GatewayDto;
}

const GatewayDetails: React.FC<GatewayDetailsProps> = ({ gateway }) => {
  // state
  const [showCreateForm, setShowCreateForm] = useState<Boolean>(false);
  // handlers
  const addDeviceHandler = useCallback(
    () => setShowCreateForm((prev) => !prev),
    []
  );
  const removeDeviceHandler = useCallback(
    (deviceId: number) => async () => {
      await deleteDevice(deviceId);
      location.reload();
    },
    []
  );
  const closeCreateFormHandler = useCallback(() => {
    setShowCreateForm(false);
  }, []);

  return (
    <div className="global-wrapper">
      <div className="v-normal-spaced">
        <a href={`/gateways`}>All gateways</a>
      </div>
      <h1 className={styles.styled + " title"}>{gateway.name}</h1>
      <div>IPv4: {gateway.ipv4Address}</div>
      <div>Serial number: {gateway.serialNumber}</div>
      <div className="title">Devices:</div>
      <button onClick={addDeviceHandler}>Add device</button>
      {gateway.devices &&
        gateway.devices.map((d) => (
          <div className="v-normal-spaced">
            <div>Vendor: {d.vendor}</div>
            <div>UID: {d.uid}</div>
            <div>Status: {d.status}</div>
            <div>Create date: {d.createDate}</div>
            <button className="v-normal-spaced" onClick={removeDeviceHandler(d.id)}>Remove</button>
          </div>
        ))}
      {!gateway.devices && <div>No devices</div>}
      {showCreateForm && <CreateDeviceForm formBackgroundClick={closeCreateFormHandler} gateway={gateway} />}
    </div>
  );
};

export default GatewayDetails;
